package com.robot.robotapocalypse.service.impl;

import com.robot.robotapocalypse.client.RobotClient;
import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.RobotDto;
import com.robot.robotapocalypse.exception.ModelNotFoundException;
import com.robot.robotapocalypse.service.RobotService;
import com.robot.robotapocalypse.util.AppUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RobotServiceImpl implements RobotService {

    @Value("${robot.url}")
    private String robotUrl;

    private final RobotClient robotClient;

    public RobotServiceImpl(RobotClient robotClient) {
        this.robotClient = robotClient;
    }

    @Override
    public AppResponse getRobots() {

        List<RobotDto> robotDtos = robotClient.getRobots();

        List<RobotDto> sortedRobots = robotDtos.stream().sorted(Comparator.comparing(RobotDto::getCategory)
                    .reversed()).collect(Collectors.toList());

        return AppUtils.buildAppResponse("Robots retrieved", true, sortedRobots, null);

    }
}
