package com.junhyeong.heroglass.domain.item;

import com.junhyeong.heroglass.domain.item.types.GlassesFrameType;
import com.junhyeong.heroglass.domain.item.types.SunglassesType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("S")
public class Sunglasses {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private SunglassesType type;

}
