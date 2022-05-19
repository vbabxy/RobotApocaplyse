package com.robot.robotapocalypse.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="survivor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Survivor survivor;

    @Column(name ="has_water")
    private boolean hasWater;

    @Column(name ="has_food")
    private boolean hasFood;

    @Column(name ="has_medication")
    private boolean hasMedication;

    @Column(name ="has_ammunition")
    private boolean hasAmmunition;

}
