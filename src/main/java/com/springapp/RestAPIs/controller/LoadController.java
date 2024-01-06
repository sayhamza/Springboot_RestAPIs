package com.springapp.RestAPIs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springapp.RestAPIs.entities.LoadEntities;
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
    public ResponseEntity<?> addLoad(@RequestBody LoadEntities load) {
    LoadEntities savedLoad = loadService.saveLoad(load);

    if (savedLoad == null) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add payload");
    } else {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response = objectMapper.createObjectNode();
        response.putPOJO("payload", savedLoad); 

        String responseBody = "Payload details added Successfully\n" + response.toPrettyString();
        // Constructing the response with the message and formatted JSON payload

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    }

    // 2
    @GetMapping("/load")
    public ResponseEntity<List<LoadEntities>> getAllLoads() {
    List<LoadEntities> loads = loadService.getAllLoads();
    return ResponseEntity.ok(loads);
    }

    //ShipperId 
    @GetMapping("/load/shipperId")
    public ResponseEntity<?> getLoadsByShipperId(@RequestParam String shipperId) {
        List<LoadEntities> loads = loadService.getLoadsByShipperId(shipperId);
        if (loads.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No loads found with shipperId: " + shipperId);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode loadsJsonArray = objectMapper.valueToTree(loads);

            ObjectNode response = objectMapper.createObjectNode();
            
            response.putArray("payload").addAll(loadsJsonArray);

            String responseBody = "Payload details of ShipperId: "+shipperId+"\n" + response.toPrettyString();

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
    }


    // 3
    @GetMapping("/load/{loadId}")
    public ResponseEntity<?> getLoadById(@PathVariable Long loadId) {
    LoadEntities load = loadService.getLoadById(loadId);
    if (load != null) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response = objectMapper.createObjectNode();
        
        ArrayNode loadArray = objectMapper.createArrayNode();
        loadArray.add(objectMapper.valueToTree(load));
        
        response.putArray("payload").addAll(loadArray);
        
        String responseBody = "Payload details of LoadId: " + loadId + "\n" + response.toPrettyString();
        
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payload found with loadId: " + loadId);
    }
    }
    
    
    // 4 



    @PutMapping("/load/{loadId}")
    public ResponseEntity<?> updateLoadById(@PathVariable Long loadId, @RequestBody LoadEntities updatedLoad) {
        LoadEntities updatedPayload = loadService.updateLoad(loadId, updatedLoad);
            if (updatedPayload != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectNode response = objectMapper.createObjectNode();
                
                ArrayNode loadArray = objectMapper.createArrayNode();
                loadArray.add(objectMapper.valueToTree(updatedPayload));
                
                response.putArray("payload").addAll(loadArray);
                
                String responseBody = "Updated Payload details of LoadId: " + loadId + "\n" + response.toPrettyString();
                
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payload found with loadId: " + loadId);
    }
} 


    
    // 5
    // @DeleteMapping("/load/{loadId}")
    // public ResponseEntity<String> deleteLoadById(@PathVariable Long loadId) {
    //     loadService.deleteLoadById(loadId);
    //     return ResponseEntity.ok("Load with ID " + loadId + " has been deleted");
    // }
    
    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<String> deleteLoadById(@PathVariable Long loadId) {
        try {
            boolean deleteStatus = loadService.deleteLoadById(loadId);
    
            if (deleteStatus) {
                return ResponseEntity.ok("Load with ID " + loadId + " has been deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No payload found with loadId: " + loadId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while deleting the load: " + loadId);
        }
    }
    
    // optional delete all

    @DeleteMapping("/load")
    public void deleteAll() {
        loadService.deleteAll();
    }
    
}
