package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.MilitaryGlassesShop;
import com.junhyeong.heroglass.repository.MilitaryGlassesShopRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilitaryGlassesShopService {
    @Autowired
    private MilitaryGlassesShopRepository militaryGlassesShopRepository;

    @Transactional
    public void saveAll(List<MilitaryGlassesShop> militaryGlassesShops) {
        militaryGlassesShopRepository.saveAll(militaryGlassesShops);
    }
}
