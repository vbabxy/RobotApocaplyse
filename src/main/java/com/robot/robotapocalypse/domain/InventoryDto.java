package com.robot.robotapocalypse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private Long survivorId;
    private Boolean hasWater;
    private Boolean hasFood;
    private Boolean hasMedication;
    private Boolean hasAmmunition;
}
