package com.springapp.RestAPIs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp.RestAPIs.entities.Load;
import com.springapp.RestAPIs.services.LoadService;



@RestController
// @RequestMapping("/load")

public class LoadController {
    
    @Autowired
    private final LoadService loadService;

    
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    // 1

    @PostMapping("/load")
    public ResponseEntity<String> addLoad(@RequestBody Load load) {
    Load savedLoad = loadService.saveLoad(load);

    if (savedLoad != null) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Payload added successfully");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add payload");
    }
    }


    // 2

    @GetMapping("/load")
    public ResponseEntity<List<Load>> getAllLoads() {
    List<Load> loads = loadService.getAllLoads();
    return ResponseEntity.ok(loads);
    }



    @GetMapping("/load/shipperId")
    public ResponseEntity<?> getLoadsByShipperId(@RequestParam String shipperId) {
        List<Load> loads = loadService.getLoadsByShipperId(shipperId);
        if (loads.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No loads found with shipperId: " + shipperId);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(loads);
        }
    }


  
    // 3

    @GetMapping("/load/{loadId}")
    public ResponseEntity<?> getLoadById(@PathVariable Long loadId) {
        Load load = loadService.getLoadById(loadId);
        if (load != null) {
            try {
                // Convert Load object to JSON string
                ObjectMapper objectMapper = new ObjectMapper();
                String loadJson = objectMapper.writeValueAsString(load);

                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(loadJson);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process JSON");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payload found with loadId: " + loadId);
        }
    }
    
    
    // 4 

    @PutMapping("/load/{loadId}")
    public ResponseEntity<?> updateLoadById(@PathVariable Long loadId, @RequestBody Load updatedLoad) {
        Load updatedPayload = loadService.updateLoad(loadId, updatedLoad);
        if (updatedPayload != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Payload with loadId " + loadId + " has been updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payload found with loadId: " + loadId);
        }
    }
    
    // 5

    // optional delete all

    @DeleteMapping("/load")
    public void deleteAll() {
        loadService.deleteAll();
    }
    
}
