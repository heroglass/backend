package com.junhyeong.heroglass.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junhyeong.heroglass.domain.AppUser;
import com.junhyeong.heroglass.domain.Delivery;
import com.junhyeong.heroglass.domain.Order;
import com.junhyeong.heroglass.domain.OrderItem;
import com.junhyeong.heroglass.domain.OrderStatus;
import com.junhyeong.heroglass.domain.dto.response.OrderItemResponse;
import com.junhyeong.heroglass.domain.item.Item;
import com.junhyeong.heroglass.domain.dto.request.OrderRequest;
import com.junhyeong.heroglass.domain.dto.response.OrderResponse;
import com.junhyeong.heroglass.domain.dto.response.PrepareOrderResponse;
import com.junhyeong.heroglass.domain.dto.request.VerificationRequest;
import com.junhyeong.heroglass.domain.dto.response.VerificationResponse;
import com.junhyeong.heroglass.repository.ItemRepository;
import com.junhyeong.heroglass.repository.OrderRepository;
import com.junhyeong.heroglass.repository.UserRepository;
import com.siot.IamportRestClient.IamportClient;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final IamportClient api;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;


    private final String api_key;
    private final String api_secret;

    public OrderService(@Value("${imp.api.key}") String api_key, @Value("${imp.api.secretkey}") String api_secret,
                        RestTemplate restTemplate, ObjectMapper objectMapper, UserRepository userRepository,
                        ItemRepository itemRepository, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.api = new IamportClient(api_key, api_secret);
        this.api_key = api_key;
        this.api_secret = api_secret;
    }

    public String getPortOneToken() throws JsonProcessingException {
        String url = "https://api.iamport.kr/users/getToken";

        // JSON 객체 생성
        String requestBody = "{\"imp_key\":\"" + api_key + "\", \"imp_secret\":\"" + api_secret + "\"}";

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // POST 요청 전송
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                String.class);

        return objectMapper.readTree(responseEntity.getBody()).path("response").path("access_token").asText();
    }

    public PrepareOrderResponse prepareOrder(String merchantUid, int amount)
            throws JsonProcessingException {

        String url = "https://api.iamport.kr/payments/prepare";

        String requestBody = "{\"merchant_uid\":\"" + merchantUid + "\", \"amount\":"
                + amount + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getPortOneToken());

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                String.class);

        return new PrepareOrderResponse(merchantUid, amount);
    }

    public OrderResponse createOrder(Long userId, OrderRequest orderRequest)
            throws JsonProcessingException {

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id:" + userId));
        Item item = itemRepository.findById(orderRequest.itemId())
                .orElseThrow(() -> new NoSuchElementException("Item not found with id:" + orderRequest.itemId()));
        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(user.getAddress());
        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderRequest.count());
        // 주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);
        prepareOrder(order.getOrderUuid(), order.getTotalPrice());

        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList());

        // 주문 저장
        orderRepository.save(order);

        return new OrderResponse(order.getOrderUuid(), orderItemResponses, order.getTotalPrice(),
                orderItem.getCount());
    }


    public VerificationResponse verification(VerificationRequest verificationRequest) throws JsonProcessingException {
        int orderAmount = getPaymentInfo(verificationRequest.imp_uid());

        Order order = orderRepository.findByOrderUuid(verificationRequest.orderUuid());

        if (orderAmount != order.getTotalPrice()) {
            order.setStatus(OrderStatus.ORDER_PAYMENT_FAIL);
            return new VerificationResponse("검증 실패", order.getOrderUuid(), orderAmount, order.getTotalPrice());
        }
        order.setStatus(OrderStatus.ORDER_COMPLETE_PAYMENT);
        return new VerificationResponse("검증 성공", order.getOrderUuid(), orderAmount, order.getTotalPrice());
    }

    private int getPaymentInfo(String imp_uid) throws JsonProcessingException {
        String url = "https://api.iamport.kr/payments/" + imp_uid;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(api_key, api_secret); // Encode key and secret for basic auth
        headers.setBearerAuth(getPortOneToken());

        // Create a GET request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        // Send GET request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                String.class);

        JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
        JsonNode responseNode = rootNode.path("response");
        int amount = responseNode.path("amount").asInt();

        return amount;


    }

    public List<OrderResponse> getOrders(Long id) {

        List<Order> orders = orderRepository.findByUserId(id).stream().toList();

        return orders.stream()
                .map(this::convertToOrderResponse)
                .collect(Collectors.toList());

    }

    private OrderResponse convertToOrderResponse(Order order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList());

        return new OrderResponse(order.getOrderUuid(), orderItemResponses,
                orderItemResponses.stream().mapToInt((orderItemResponse) -> orderItemResponse.count()).sum(),
                order.getTotalPrice());
    }

    private OrderItemResponse convertToOrderItemDTO(OrderItem item) {
        return new OrderItemResponse(item.getItem().getId(), item.getItem().getName(),
                item.getItem().getStockQuantity(),
                item.getItem().getPrice(), item.getCount());
    }


}
