package com.robot.robotapocalypse.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotDto {

    private String model;
    private String serialNumber;
    private String manufacturerDate;
    private String category;
}
