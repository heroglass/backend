package com.junhyeong.heroglass.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class glasses {
    @Id
    @GeneratedValue
    private Long id;


}
