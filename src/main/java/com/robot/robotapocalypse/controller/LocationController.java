package com.robot.robotapocalypse.controller;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.LocationDto;
import com.robot.robotapocalypse.model.Location;
import com.robot.robotapocalypse.service.LocationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/locations")
@Api(value = "/api/v1/locations", tags = "locations")
public class LocationController {

    private final LocationService locationService;


    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<AppResponse> getLocations(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Location> locationPage = locationService.getLocations(page, size);

        AppResponse appResponse = AppResponse.builder()
                .success(true)
                .message("Location Retrieved")
                .error(null)
                .data(locationPage)
                .build();

        return ResponseEntity.ok(appResponse);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<AppResponse> getLocation(@PathVariable("locationId") Long locationId) {
        Location location = locationService.getLocation(locationId);

        AppResponse appResponse = AppResponse.builder()
                .success(true)
                .message("Location Retrieved")
                .error(null)
                .data(location)
                .build();

        return ResponseEntity.ok(appResponse);
    }

    @PostMapping
    public ResponseEntity<AppResponse> createLocation(@RequestBody LocationDto locationDto) {
        Location location = locationService.createLocation(locationDto);

        AppResponse appResponse = AppResponse.builder()
                .success(true)
                .message("Location Created")
                .error(null)
                .data(location)
                .build();

        return ResponseEntity.ok(appResponse);
    }
}
