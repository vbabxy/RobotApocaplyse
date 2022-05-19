package com.robot.robotapocalypse.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LocationDto {

    private BigDecimal longitude;

    private BigDecimal latitude;
}
