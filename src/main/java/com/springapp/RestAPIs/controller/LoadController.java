package com.springapp.RestAPIs.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springapp.RestAPIs.entities.Load;
import com.springapp.RestAPIs.services.LoadService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/load")
public class LoadController {
    
    @Autowired
    private final LoadService loadService;

    
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    // GET all loads or by shipperId if provided
    @GetMapping
    public List<Load> getLoadsByShipperId(@RequestParam(required = false) UUID shipperId) {
        if (shipperId != null) {
            return loadService.getLoadsByShipperId(shipperId);
        } else {
            return loadService.getAllLoads();
        }
    }

    // GET load by ID
    @GetMapping("/{loadId}")
    public Load getLoadById(@PathVariable Long loadId) {
        return loadService.getLoadById(loadId);
    }

    // POST to add a new load
    @PostMapping
    public Load addLoad(@RequestBody Load load) {
        return loadService.saveLoad(load);
    }

    // PUT to update load details by ID
    @PutMapping("/{loadId}")
    public Load updateLoadById(@PathVariable Long loadId, @RequestBody Load updatedLoad) {
        Load existingLoad = loadService.getLoadById(loadId);
        if (existingLoad != null) {
            // Update existing load details
            // Assuming setters are available in Load class
            existingLoad.setLoadingPoint(updatedLoad.getLoadingPoint());
            existingLoad.setUnloadingPoint(updatedLoad.getUnloadingPoint());
            // Update other fields accordingly
            // Save and return the updated load
            return loadService.saveLoad(existingLoad);
        }
        return null; // Handle when load ID doesn't exist
    }

    // DELETE load by ID
    @DeleteMapping("/{loadId}")
    public void deleteLoadById(@PathVariable Long loadId) {
        loadService.deleteLoadById(loadId);
    }

    // Other endpoints as needed...
}
