package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.MilitaryGlassesShop;
import com.junhyeong.heroglass.dto.ShopRequest;
import com.junhyeong.heroglass.dto.ShopResponse;
import com.junhyeong.heroglass.repository.MilitaryGlassesShopRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilitaryGlassesShopService {
    @Autowired
    private MilitaryGlassesShopRepository militaryGlassesShopRepository;

    @Transactional
    public void saveAll(List<ShopRequest> shopRequests) {
        List<MilitaryGlassesShop> militaryGlassesShops = shopRequests.stream()
                .map(request -> new MilitaryGlassesShop(
                        request.rowno(),
                        request.shop(),
                        request.city(),
                        request.district(),
                        request.telno(),
                        request.postno(),
                        request.address(),
                        request.addressdetail()
                ))
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
