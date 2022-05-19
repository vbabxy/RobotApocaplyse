package com.robot.robotapocalypse.controller;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.request.RegisterSurvivorRequestDto;
import com.robot.robotapocalypse.service.InfectedReportService;
import com.robot.robotapocalypse.service.SurvivorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/v1/survivors")
@Api(value = "/api/v1/survivors", tags = "survivors")
public class SurvivorController {

    private final SurvivorService survivorService;
    private final InfectedReportService infectedReportService;

    public SurvivorController(SurvivorService survivorService, InfectedReportService infectedReportService) {
        this.survivorService = survivorService;
        this.infectedReportService = infectedReportService;
    }

    @PostMapping
    @ApiOperation(value = "Register Survivor",notes = "Register Survivor")
    public ResponseEntity<AppResponse> registerSurvivor(@RequestBody @Valid RegisterSurvivorRequestDto requestDto) {
        return ResponseEntity.ok(survivorService.registerSurvivor(requestDto));
    }

    @PatchMapping("/{id}/locatiionId/{locationId}")
    public ResponseEntity<AppResponse> updateSurvivorLocation(@PathVariable("id") Long id,
                                                              @PathVariable("locationId") Long locationId) {
        return ResponseEntity.ok(survivorService.updateExistingSurvivorLocation(id, locationId));
    }

    @PatchMapping("/{reporterId}/flag/{reportedId}")
    public ResponseEntity<AppResponse> flagSurvivor(@PathVariable("reporterId") Long reporterId,
                                                    @PathVariable("reportedId") Long reportedId) {

       return ResponseEntity.accepted().body(infectedReportService.logInfectedReport(reporterId, reportedId));

    }
}
