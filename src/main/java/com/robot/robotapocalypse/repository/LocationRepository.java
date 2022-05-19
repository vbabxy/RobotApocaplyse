package com.robot.robotapocalypse.repository;

import com.robot.robotapocalypse.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
