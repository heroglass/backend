package com.junhyeong.heroglass.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class sunglasses {

    @GeneratedValue
    @Id
    private Long id;
}
