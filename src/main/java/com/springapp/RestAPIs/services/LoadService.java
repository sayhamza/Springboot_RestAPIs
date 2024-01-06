package com.springapp.RestAPIs.services;

import java.util.List;
import com.springapp.RestAPIs.entities.LoadEntities;

public interface LoadService {
    

    LoadEntities saveLoad(LoadEntities load);

    List<LoadEntities> getAllLoads();

    List<LoadEntities> getLoadsByShipperId(String shipperId);

    LoadEntities getLoadById(Long load);

    LoadEntities updateLoad(Long loadId, LoadEntities updatedLoad);
    
    boolean deleteLoadById(Long id);

    void deleteAll();

}
