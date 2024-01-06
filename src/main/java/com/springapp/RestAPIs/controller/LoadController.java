package com.springapp.RestAPIs.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapp.RestAPIs.entities.Load;
import com.springapp.RestAPIs.services.LoadService;

import java.util.List;

@RestController
@RequestMapping("/api/load")
public class LoadController {
    
    @Autowired
    private final LoadService loadService;

    
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    // 1

    @PostMapping
    public ResponseEntity<String> addLoad(@RequestBody Load load) {
    Load savedLoad = loadService.saveLoad(load);

    if (savedLoad != null) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Payload added successfully");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add payload");
    }
    }


    // 2


    @GetMapping
    public ResponseEntity<List<Load>> getLoadsByShipperId(@RequestParam(required = false) String shipperId) {
    List<Load> loads;

    if (shipperId != null) {
        loads = loadService.getLoadsByShipperId(shipperId);
    } else {
        loads = loadService.getAllLoads();
    }

    if (loads.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Or return an appropriate message
    } else {
        return ResponseEntity.status(HttpStatus.OK).body(loads);
    }
    }


    
    // 3

    @GetMapping("/load/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable Long loadId) {
    Load load = loadService.getLoadById(loadId);

    if (load != null) {
        return ResponseEntity.status(HttpStatus.OK).body(load);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Or return an appropriate message
    }
    }

    // 4 

    @PutMapping("/load/{loadId}")
    public ResponseEntity<Load> updateLoadById(@PathVariable Long loadId, @RequestBody Load updatedLoad) {
    Load existingLoad = loadService.getLoadById(loadId);

    if (existingLoad != null) {
        existingLoad.setLoadingPoint(updatedLoad.getLoadingPoint());
        existingLoad.setUnloadingPoint(updatedLoad.getUnloadingPoint());
        existingLoad.setProductType(updatedLoad.getProductType());
        existingLoad.setTruckType(updatedLoad.getTruckType());
        existingLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
        existingLoad.setWeight(updatedLoad.getWeight());
        existingLoad.setComment(updatedLoad.getComment());
        existingLoad.setDate(updatedLoad.getDate()); // Assuming 'Date' is a property in the Load class

        Load updatedLoadEntity = loadService.saveLoad(existingLoad);
        return ResponseEntity.status(HttpStatus.OK).body(updatedLoadEntity);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Or return an appropriate message
    }
}
    // 5

    @DeleteMapping("/load/{loadId}")
    public void deleteLoadById(@PathVariable Long loadId) {
        loadService.deleteLoadById(loadId);
    }

    // optional delete all

    @DeleteMapping
    public void deleteAll() {
        loadService.deleteAll();
    }
    
}
