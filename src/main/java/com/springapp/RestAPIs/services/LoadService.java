package com.springapp.RestAPIs.services;

import java.util.List;
import com.springapp.RestAPIs.entities.Load;

public interface LoadService {

    Load saveLoad(Load load);

    List<Load> getAllLoads();

    List<Load> getLoadsByShipperId(String shipperId);

    Load getLoadById(Long load);

    Load updateLoad(Long loadId, Load updatedLoad);
    
    void deleteLoadById(Long id);

    void deleteAll();
    
}
