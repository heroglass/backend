package com.junhyeong.heroglass.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class MilitaryGlassesShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rowno;
    private String shop;
    private String city;
    private String district;
    private String telno;
    private String postno;
    private String address;
    private String addressdetail;

}
