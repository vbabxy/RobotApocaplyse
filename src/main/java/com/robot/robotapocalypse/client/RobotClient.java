package com.robot.robotapocalypse.client;


import com.robot.robotapocalypse.domain.RobotDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "robot-client", url = "${robot.url}")
public interface RobotClient {

    @GetMapping
    List<RobotDto> getRobots();
}
