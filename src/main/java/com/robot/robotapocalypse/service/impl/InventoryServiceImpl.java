package com.robot.robotapocalypse.service.impl;

import com.robot.robotapocalypse.exception.ModelAlreadyExistException;
import com.robot.robotapocalypse.model.Inventory;
import com.robot.robotapocalypse.repository.InventoryRepository;
import com.robot.robotapocalypse.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory createSurvivorInventory(Inventory inventory) {
        log.info("about to save inventory for survivor {} ", inventory.getSurvivor().getId());

        inventoryRepository.findBySurvivor(inventory.getSurvivor()).ifPresent(alreadyExist -> {
            throw new ModelAlreadyExistException("survivor already exist");
        });

        return inventoryRepository.save(inventory);
    }
}
