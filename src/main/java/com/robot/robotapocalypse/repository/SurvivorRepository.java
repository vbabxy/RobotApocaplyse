package com.robot.robotapocalypse.repository;

import com.robot.robotapocalypse.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SurvivorRepository extends JpaRepository<Survivor, Long> {

    @Query("select x from survivor x where x.name = ?1")
    Optional<Survivor> findByName(String name);
}
