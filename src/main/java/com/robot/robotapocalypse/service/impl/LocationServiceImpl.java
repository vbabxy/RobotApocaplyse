package com.robot.robotapocalypse.service.impl;

import com.robot.robotapocalypse.domain.LocationDto;
import com.robot.robotapocalypse.exception.BadRequestException;
import com.robot.robotapocalypse.exception.ModelNotFoundException;
import com.robot.robotapocalypse.model.Location;
import com.robot.robotapocalypse.repository.LocationRepository;
import com.robot.robotapocalypse.service.LocationService;
import com.robot.robotapocalypse.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Page<Location> getLocations(int page, int size) {
        log.info("about to validate the page request for location {} {} ", page, size);

        if(!AppUtils.pageValidator(page, size)) {
            throw new BadRequestException("page and size must be greater than zero");
        }
        Pageable pageable = PageRequest.of(page - 1 , size);

        return locationRepository.findAll(pageable);
    }

    @Override
    public Location getLocation(Long id) {
        log.info("about to get the details of a location by id {} ", id);
        return locationRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Selected location does not exist"));
    }

    @Override
    public Location createLocation(LocationDto locationDto) {
        log.info("about to create a new location {} ", locationDto);
        Location location = Location.builder()
                .longitude(locationDto.getLongitude())
                .latitude(locationDto.getLatitude())
                .build();
        return locationRepository.save(location);
    }
}
