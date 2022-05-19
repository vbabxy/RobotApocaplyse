package com.robot.robotapocalypse.service;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.request.RegisterSurvivorRequestDto;
import com.robot.robotapocalypse.model.Survivor;

public interface SurvivorService {

    AppResponse registerSurvivor(RegisterSurvivorRequestDto registerSurvivorRequestDto);

    AppResponse updateExistingSurvivorLocation(Long id, Long locationId);

    Survivor getSurvivor(Long id);

    Survivor flagSurvivor(Survivor reported);
}
