package com.junhyeong.heroglass.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
