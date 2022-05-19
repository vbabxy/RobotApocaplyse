package com.robot.robotapocalypse.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "infection_report")
public class InfectionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="reporter")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Survivor reporter;

    @ManyToOne
    @JoinColumn(name="reported")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Survivor reported;

}
