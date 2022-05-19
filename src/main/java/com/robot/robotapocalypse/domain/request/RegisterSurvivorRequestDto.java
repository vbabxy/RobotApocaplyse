package com.robot.robotapocalypse.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSurvivorRequestDto {

    @NotBlank(message = "name must be provided")
    private String name;
    @Positive(message = "age must be provided")
    private Integer age;
    @NotBlank(message = "gender must be provided")
    private String gender;
    @NotNull(message = "location id must be selected")
    private Long locationId;
    private Boolean hasWater;
    private Boolean hasFood;
    private Boolean hasMedication;
    private Boolean hasAmmunition;

}
