package com.junhyeong.heroglass.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Vision {
    private double leftEye;
    private double rightEye;

    public Vision(double leftEye, double rightEye) {
        this.leftEye = leftEye;
        this.rightEye = rightEye;
    }

    protected Vision() {
    }
}
