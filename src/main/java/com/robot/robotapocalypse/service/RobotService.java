package com.robot.robotapocalypse.service;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.RobotDto;
import jdk.jfr.Category;

import java.util.List;

public interface RobotService {

    AppResponse getRobots();
}
