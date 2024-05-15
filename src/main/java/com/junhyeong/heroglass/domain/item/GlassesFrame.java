package com.junhyeong.heroglass.domain.item;

import com.junhyeong.heroglass.domain.item.types.GlassesFrameType;
import com.junhyeong.heroglass.domain.item.types.GlassesType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class GlassesFrame {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private GlassesFrameType type;

}
