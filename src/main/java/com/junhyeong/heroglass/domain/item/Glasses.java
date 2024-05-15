package com.junhyeong.heroglass.domain.item;

import com.junhyeong.heroglass.domain.item.types.GlassesType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("G")
public class Glasses extends Item {
    private String etc;

    @Enumerated(EnumType.STRING)
    private GlassesType type;

}
