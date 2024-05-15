package com.junhyeong.heroglass.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;

@Entity
@Getter
public class lens {
    @Id
    @GeneratedValue
    private Long id;

}
