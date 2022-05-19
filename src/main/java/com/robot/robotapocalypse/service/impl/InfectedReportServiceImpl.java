package com.robot.robotapocalypse.service.impl;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.exception.ModelAlreadyExistException;
import com.robot.robotapocalypse.model.InfectionReport;
import com.robot.robotapocalypse.model.Survivor;
import com.robot.robotapocalypse.repository.InfectionReportRepository;
import com.robot.robotapocalypse.service.InfectedReportService;
import com.robot.robotapocalypse.service.SurvivorService;
import com.robot.robotapocalypse.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class InfectedReportServiceImpl implements InfectedReportService {

    private final SurvivorService survivorService;
    private final InfectionReportRepository infectionReportRepository;

    public InfectedReportServiceImpl(SurvivorService survivorService,
                                     InfectionReportRepository infectionReportRepository) {
        this.survivorService = survivorService;
        this.infectionReportRepository = infectionReportRepository;
    }

    @Override
    public AppResponse logInfectedReport(Long reporterId, Long reportedId) {
        log.info("about to report an infected person");

        Survivor reporter = survivorService.getSurvivor(reporterId);

        Survivor reported = survivorService.getSurvivor(reportedId);

        List<InfectionReport> infectedResult = infectionReportRepository.findInfectionReportByReported(reported);

        if(infectedResult.size() < 3) {
            infectionReportRepository.findInfectionReportByReporterAndReported(reporter, reported)
                    .ifPresent(alreadyExist -> {
                        throw new ModelAlreadyExistException("Survivor already reported by the reporter");
                    });

            InfectionReport infectionReport = new InfectionReport();
            infectionReport.setReporter(reporter);
            infectionReport.setReported(reported);
            infectionReportRepository.save(infectionReport);
        }else {
            log.info("flag the reported has infected");
            reported = survivorService.flagSurvivor(reported);
        }

        return AppUtils.buildAppResponse("Survivor Reported", true, reported, null);
    }
}
