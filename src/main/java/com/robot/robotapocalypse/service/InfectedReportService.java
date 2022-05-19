package com.robot.robotapocalypse.service;

import com.robot.robotapocalypse.domain.AppResponse;

public interface InfectedReportService {

    AppResponse logInfectedReport(Long reporterId, Long reportedId);
}
