package com.junhyeong.heroglass.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("SUNGLASSES")
public class Sunglasses extends Item {

    private String etc;
}
