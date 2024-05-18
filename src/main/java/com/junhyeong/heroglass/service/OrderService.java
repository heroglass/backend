package com.junhyeong.heroglass.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junhyeong.heroglass.dto.OrderResponse;
import com.junhyeong.heroglass.dto.PrepareOrderRequest;
import com.junhyeong.heroglass.dto.PrepareOrderResponse;
import com.siot.IamportRestClient.IamportClient;
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

    private final String api_key;
    private final String api_secret;

    public OrderService(@Value("${imp.api.key}") String api_key, @Value("${imp.api.secretkey}") String api_secret,
                        RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
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

    public PrepareOrderResponse prepareOrder(Long id, PrepareOrderRequest prepareOrderRequest)
            throws JsonProcessingException {

        String url = "https://api.iamport.kr/payments/prepare";

        String requestBody = "{\"merchant_uid\":\"" + prepareOrderRequest.merchantUid() + "\", \"amount\":"
                + prepareOrderRequest.amount() + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getPortOneToken());

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                String.class);

        return new PrepareOrderResponse(prepareOrderRequest.merchantUid(), prepareOrderRequest.amount());
    }

    public OrderResponse createOrder() {

        return new OrderResponse();
    }


}
