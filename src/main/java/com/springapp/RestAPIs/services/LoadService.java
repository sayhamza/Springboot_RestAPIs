package com.springapp.RestAPIs.services;

import java.util.List;
import java.util.UUID;

import com.springapp.RestAPIs.entities.Load;

public interface LoadService {
    
    List<Load> getAllLoads();
    List<Load> getLoadsByShipperId(UUID shipperId);
    Load getLoadById(Long loadId);
    Load saveLoad(Load load);
    void deleteLoadById(Long loadId);
    void deleteAll();
}

