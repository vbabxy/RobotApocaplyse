package com.robot.robotapocalypse.service;

import com.robot.robotapocalypse.domain.LocationDto;
import com.robot.robotapocalypse.model.Location;
import org.springframework.data.domain.Page;

public interface LocationService {

    Page<Location> getLocations(int page, int size);

    Location getLocation(Long id);

    Location createLocation(LocationDto locationDto);
}
