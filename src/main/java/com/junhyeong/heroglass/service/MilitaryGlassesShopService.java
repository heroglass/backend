package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.MilitaryGlassesShop;
import com.junhyeong.heroglass.domain.dto.request.ShopRequest;
import com.junhyeong.heroglass.domain.dto.response.ShopResponse;
import com.junhyeong.heroglass.repository.MilitaryGlassesShopRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilitaryGlassesShopService {

    private final MilitaryGlassesShopRepository militaryGlassesShopRepository;
    private final GeocodingService geocodingService;

    @Transactional
    public void saveAll(List<ShopRequest> shopRequests) {
        List<MilitaryGlassesShop> militaryGlassesShops = shopRequests.stream()
                .map(request -> {
                    try {
                        return new MilitaryGlassesShop(
                                request.rowno(),
                                request.shop(),
                                request.city(),
                                request.district(),
                                request.telno(),
                                request.postno(),
                                request.address(),
                                request.addressdetail(),
                                geocodingService.getAddressCoordinates(request.address() + " " + request.addressdetail())

                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        militaryGlassesShopRepository.saveAll(militaryGlassesShops);
    }

    public List<ShopResponse> findAllShops() {

        List<ShopResponse> shopResponses = militaryGlassesShopRepository.findAll().stream()
                .map(response -> new ShopResponse(response.getRowno(), response.getShop(), response.getCity(),
                        response.getDistrict(), response.getTelno(), response.getPostno(), response.getAddress(),
                        response.getAddressdetail())).collect(Collectors.toList());
        return shopResponses;
    }
}
