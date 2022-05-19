package com.robot.robotapocalypse.repository;

import com.robot.robotapocalypse.model.Inventory;
import com.robot.robotapocalypse.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("select x from inventory x where x.survivor = ?1")
    Optional<Inventory> findBySurvivor(Survivor survivor);
}
