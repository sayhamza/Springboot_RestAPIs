package com.springapp.RestAPIs.services;

import java.util.List;

import com.springapp.RestAPIs.entities.Load;

public interface LoadService {

    void deleteAll();
    List<Load> getAllLoads();
    List<Load> getLoadsByShipperId(String shipperId);
    Load getLoadById(Long loadId);
    Load saveLoad(Load load);
    void deleteLoadById(Long loadId);



}
