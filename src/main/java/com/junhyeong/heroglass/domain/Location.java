package com.junhyeong.heroglass.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
