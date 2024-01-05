package com.springapp.RestAPIs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapp.RestAPIs.entities.LoadsApi;
import com.springapp.RestAPIs.repository.LoadsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoadsController {

    @Autowired
    private final LoadsRepository loadRepository;

    
    public LoadsController(LoadsRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    @GetMapping
    public List<LoadsApi> getAllLoadsByShipperId(@RequestParam String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    @PostMapping("/load")
    public ResponseEntity<String> addLoad(@RequestBody LoadsApi load) {
        try {
            LoadsApi savedLoad = loadRepository.save(load);
            if (savedLoad != null) {
                return ResponseEntity.ok("Load details added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add load details");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding load details: " + e.getMessage());
        }
    }
 
    @GetMapping("/load")
    public ResponseEntity<List<LoadsApi>> getLoadsByShipperId(@RequestParam("shipperId") String shipperId) {
        try {
            List<LoadsApi> loads = loadRepository.findByShipperId(shipperId);
            return ResponseEntity.ok(loads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/load/{loadId}")
    public ResponseEntity<LoadsApi> getLoadById(@PathVariable Long loadId) {
        Optional<LoadsApi> load = loadRepository.findById(loadId);
        return load.map(value -> ResponseEntity.ok().body(value))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/load/{loadId}")
    public ResponseEntity<String> updateLoad(@PathVariable Long loadId, @RequestBody LoadsApi updatedLoad) {
        try {
            Optional<LoadsApi> optionalLoad = loadRepository.findById(loadId);
            if (optionalLoad.isPresent()) {
                updatedLoad.setId(loadId);
                loadRepository.save(updatedLoad);
                return ResponseEntity.ok("Load details updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating load details: " + e.getMessage());
        }
    }

    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<String> deleteLoad(@PathVariable Long loadId) {
        try {
            Optional<LoadsApi> optionalLoad = loadRepository.findById(loadId);
            if (optionalLoad.isPresent()) {
                loadRepository.deleteById(loadId);
                return ResponseEntity.ok("Load deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting load: " + e.getMessage());
        }
    }
}

