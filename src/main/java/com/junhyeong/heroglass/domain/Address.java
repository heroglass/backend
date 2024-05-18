package com.junhyeong.heroglass.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String address;
    private String detail;

    protected Address() {
    }

    public Address(String address, String detail) {
        this.address = address;
        this.detail = detail;
    }

}
