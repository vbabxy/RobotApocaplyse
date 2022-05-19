package com.robot.robotapocalypse.service;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.PercentageReportDto;
import com.robot.robotapocalypse.domain.RobotDto;
import com.robot.robotapocalypse.model.Survivor;
import com.robot.robotapocalypse.repository.SurvivorRepository;
import com.robot.robotapocalypse.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportService {

    private final SurvivorRepository survivorRepository;
    private final RobotService robotService;

    public ReportService(SurvivorRepository survivorRepository, RobotService robotService) {
        this.survivorRepository = survivorRepository;
        this.robotService = robotService;
    }


    public AppResponse getPercentageReport() {
        log.info("about to get percentage report of both infected and non infected survivors");

        List<Survivor> survivors = survivorRepository.findAll();

        long infectedSurvivors = survivors.stream()
                .filter(survivor -> survivor.isInfected()).count();

        long survivorCount = survivors.stream().count();

        double percentageOfInfectedSurvivors = calculatePercentage(infectedSurvivors, survivorCount);

        long nonInfectedSurvivors = survivorCount - infectedSurvivors;

        double percentageOfNonInfectedSurvivors = calculatePercentage(nonInfectedSurvivors, survivorCount);

        PercentageReportDto percentageReportDto = PercentageReportDto.builder()
                .percentageOfInfectedSurvivors(percentageOfInfectedSurvivors)
                .percentageOfNonInfectedSurvivors(percentageOfNonInfectedSurvivors)
                .build();

        return AppUtils.buildAppResponse("Percentage Reports", true, percentageReportDto, null);

    }


    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }

    public AppResponse getInfectedSurvivors() {

        List<Survivor> survivors = survivorRepository.findAll();

        List<Survivor> infectedSurvivors = survivors.stream()
                .filter(survivor -> survivor.isInfected()).collect(Collectors.toList());
        return AppUtils.buildAppResponse("Percentage Reports", true, infectedSurvivors, null);
    }

    public AppResponse getNonInfectedSurvivors() {

        List<Survivor> survivors = survivorRepository.findAll();

        List<Survivor> nonInfectedSurvivors = survivors.stream()
                .filter(survivor -> !survivor.isInfected()).collect(Collectors.toList());
        return AppUtils.buildAppResponse("Percentage Reports", true, nonInfectedSurvivors, null);
    }

    public AppResponse getListOfRobots() {
        return robotService.getRobots();
    }
}
