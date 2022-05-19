package com.robot.robotapocalypse.controller;


import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.service.ReportService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1")
@Api(value = "/api/v1/", tags = "reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/percentage/reports")
    public ResponseEntity<AppResponse> getPercentageReport() {
        return ResponseEntity.ok(reportService.getPercentageReport());
    }

    @GetMapping("/infected/survivors/reports")
    public ResponseEntity<AppResponse> getInfectedSurvivors() {
        return ResponseEntity.ok(reportService.getInfectedSurvivors());
    }

    @GetMapping("/nonInfected/survivors/reports")
    public ResponseEntity<AppResponse> getNonInfectedSurvivors() {
        return ResponseEntity.ok(reportService.getNonInfectedSurvivors());
    }

    @GetMapping("/robots/reports")
    public ResponseEntity<AppResponse> getListOfRobots() {
        return ResponseEntity.ok(reportService.getListOfRobots());
    }

}
