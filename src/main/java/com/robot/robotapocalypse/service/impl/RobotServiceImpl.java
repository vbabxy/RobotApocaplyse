package com.robot.robotapocalypse.service.impl;

import com.robot.robotapocalypse.client.RobotClient;
import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.LocationDto;
import com.robot.robotapocalypse.domain.RobotDto;
import com.robot.robotapocalypse.model.Location;
import com.robot.robotapocalypse.service.LocationService;
import com.robot.robotapocalypse.service.RobotService;
import com.robot.robotapocalypse.util.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RobotServiceImpl implements RobotService {

    private final RobotClient robotClient;

    private final LocationService locationService;

    public RobotServiceImpl(RobotClient robotClient, LocationService locationService) {
        this.robotClient = robotClient;
        this.locationService = locationService;
    }

    @Override
    public AppResponse getRobots() {

        List<RobotDto> robotDtos = robotClient.getRobots();


        robotDtos.stream().forEach(robotDto ->
                robotDto.setLocationDto(generateRandomLocation()));

        List<RobotDto> sortedRobots = robotDtos.stream()
                .sorted(Comparator.comparing(RobotDto::getCategory)
                    .reversed()).collect(Collectors.toList());

        return AppUtils.buildAppResponse("Robots retrieved", true, sortedRobots, null);

    }


    private Location generateRandomLocation() {
        Page<Location> locationPage = locationService.getLocations(1, 100);

        Random rand = new Random();
        return locationPage.getContent().get(rand.nextInt(locationPage.getContent().size()));
    }
}
