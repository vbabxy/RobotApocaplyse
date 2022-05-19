package com.robot.robotapocalypse.domain;

import com.robot.robotapocalypse.enums.Gender;
import com.robot.robotapocalypse.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurvivorDto implements Serializable {

    private String name;
    private Integer age;
    private Gender gender;
    private Long id;
    private Long locationId;
    private Boolean isInfected;

}
