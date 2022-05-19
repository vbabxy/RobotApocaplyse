package com.robot.robotapocalypse.repository;

import com.robot.robotapocalypse.model.InfectionReport;
import com.robot.robotapocalypse.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InfectionReportRepository extends JpaRepository<InfectionReport, Long> {

    @Query("select x from infection_report x where x.reporter = ?1 and x.reported = ?2")
    Optional<InfectionReport> findInfectionReportByReporterAndReported(Survivor reporter, Survivor reported);

    @Query("select x from infection_report x where x.reported =?1")
    List<InfectionReport> findInfectionReportByReported(Survivor reported);
}
