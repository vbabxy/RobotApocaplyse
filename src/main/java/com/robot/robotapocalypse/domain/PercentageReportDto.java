package com.robot.robotapocalypse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PercentageReportDto {

    private double percentageOfInfectedSurvivors;

    private double percentageOfNonInfectedSurvivors;
}
