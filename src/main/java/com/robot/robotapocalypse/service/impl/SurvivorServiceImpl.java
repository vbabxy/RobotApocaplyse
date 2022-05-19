package com.robot.robotapocalypse.service.impl;

import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.domain.SurvivorDto;
import com.robot.robotapocalypse.domain.request.RegisterSurvivorRequestDto;

import com.robot.robotapocalypse.enums.Gender;
import com.robot.robotapocalypse.exception.ModelAlreadyExistException;
import com.robot.robotapocalypse.exception.ModelNotFoundException;
import com.robot.robotapocalypse.model.Location;
import com.robot.robotapocalypse.model.Survivor;
import com.robot.robotapocalypse.repository.SurvivorRepository;
import com.robot.robotapocalypse.service.InventoryService;
import com.robot.robotapocalypse.service.LocationService;
import com.robot.robotapocalypse.service.SurvivorService;
import com.robot.robotapocalypse.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SurvivorServiceImpl implements SurvivorService {

    private final LocationService locationService;
    private final InventoryService inventoryService;
    private final SurvivorRepository survivorRepository;


    public SurvivorServiceImpl(LocationService locationService, InventoryService inventoryService,
                               SurvivorRepository survivorRepository) {
        this.locationService = locationService;
        this.inventoryService = inventoryService;
        this.survivorRepository = survivorRepository;
    }

    @Override
    public AppResponse registerSurvivor(RegisterSurvivorRequestDto registerSurvivorRequestDto) {
        log.info("about to register new survivor {} ", registerSurvivorRequestDto.getName());

        survivorRepository.findByName(registerSurvivorRequestDto.getName()).ifPresent(alreadyExist -> {
            throw new ModelAlreadyExistException("survivor already exist");
        });

        Location location = locationService.getLocation(registerSurvivorRequestDto.getLocationId());

        Gender gender = Gender.getGender(registerSurvivorRequestDto.getGender())
                .orElseThrow(() -> new ModelNotFoundException("Gender does not exist"));

        SurvivorDto survivorDto = getSurvivorDto(registerSurvivorRequestDto, location, gender);

        return AppUtils.buildAppResponse("Registered Successfully", true, survivorDto, null);
    }

    private SurvivorDto getSurvivorDto(RegisterSurvivorRequestDto registerSurvivorRequestDto, Location location, Gender gender) {
        Survivor survivor = new Survivor();
        survivor.setAge(registerSurvivorRequestDto.getAge());
        survivor.setGender(gender);
        survivor.setInfected(false);
        survivor.setLocation(location);
        survivor.setName(registerSurvivorRequestDto.getName());

        survivor = survivorRepository.save(survivor);
        return buildSurvivorDto(survivor);
    }

    private SurvivorDto buildSurvivorDto(Survivor survivor) {
        return SurvivorDto.builder()
                .age(survivor.getAge())
                .gender(survivor.getGender())
                .isInfected(survivor.isInfected())
                .locationId(survivor.getLocation().getId())
                .name(survivor.getName())
                .build();
    }

    @Override
    public AppResponse updateExistingSurvivorLocation(Long id, Long locationId) {
        log.info("about to update a survivor location {} ", id);

        Survivor survivor = getSurvivor(id);
        Location location = locationService.getLocation(locationId);

        survivor.setLocation(location);

        survivor = survivorRepository.save(survivor);

        SurvivorDto survivorDto = buildSurvivorDto(survivor);

        return AppUtils.buildAppResponse("Registered Successfully", true, survivorDto, null);
    }

    @Override
    public Survivor getSurvivor(Long id) {
        Survivor survivor = survivorRepository.findById(id)
                .orElseThrow(()-> new ModelNotFoundException("selected survivor does not exist"));
        return survivor;
    }

    @Override
    public Survivor flagSurvivor(Survivor reported) {
        log.info("about to flag the reported as infected {} ", reported.getId());
        reported.setInfected(true);
        return survivorRepository.save(reported);
    }
}
