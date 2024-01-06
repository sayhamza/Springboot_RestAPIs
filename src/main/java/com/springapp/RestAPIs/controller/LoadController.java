package com.springapp.RestAPIs.controller;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Load> getLoadsByShipperId(@RequestParam(required = false) String shipperId) {
        if (shipperId != null) {
            return loadService.getLoadsByShipperId(shipperId);
        } else {
            return loadService.getAllLoads();
        }
    }

    @GetMapping("/{loadId}")
    public Load getLoadById(@PathVariable Long loadId) {
        return loadService.getLoadById(loadId);
    }

    @PostMapping
    public Load addLoad(@RequestBody Load load) {
        return loadService.saveLoad(load);
    }

    @DeleteMapping("/{loadId}")
    public void deleteLoadById(@PathVariable Long loadId) {
        loadService.deleteLoadById(loadId);
    }

    @DeleteMapping
    public void deleteAll() {
        loadService.deleteAll();
    }
    
}
