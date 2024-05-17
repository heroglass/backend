package com.junhyeong.heroglass.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Getter
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

    @Embedded
    private Location location;

    public MilitaryGlassesShop(String rowno, String shop, String city, String district, String telno, String postno,
                               String address, String addressDetail, Location location) {
        this.rowno = rowno;
        this.shop = shop;
        this.city = city;
        this.district = district;
        this.telno = telno;
        this.postno = postno;
        this.address = address;
        this.addressdetail = addressDetail;
        this.location = location;
    }
}
