package com.junhyeong.heroglass.domain.item;


import com.junhyeong.heroglass.domain.CategoryItem;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("GLASSES_FRAME")
public class GlassesFrame extends Item {

    private String etc;

}
