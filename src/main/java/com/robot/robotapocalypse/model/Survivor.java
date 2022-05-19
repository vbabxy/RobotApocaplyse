package com.robot.robotapocalypse.model;


import com.robot.robotapocalypse.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="survivor")
public class Survivor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "is_infected")
    private boolean isInfected;

    @ManyToOne
    @JoinColumn(name="location_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location location;



}
