package com.robot.robotapocalypse.controller;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.enums.RobotCategory;
import com.robot.robotapocalypse.service.RobotService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/robots")
@Api(value = "/api/v1/robots", tags = "robots")
public class RobotController {

    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping
    public ResponseEntity<AppResponse> getRobots() {
        return ResponseEntity.ok(robotService.getRobots());
    }
}
