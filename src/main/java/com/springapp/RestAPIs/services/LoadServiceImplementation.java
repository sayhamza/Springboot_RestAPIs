package com.springapp.RestAPIs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springapp.RestAPIs.entities.LoadEntities;
import com.springapp.RestAPIs.repository.LoadRepository;
import java.util.List;

@Service
public class LoadServiceImplementation implements LoadService {

    @Autowired
    private final LoadRepository loadRepository;

    
    public LoadServiceImplementation(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }
    
    // 1
    @Override
    public LoadEntities saveLoad(LoadEntities load) {
        return loadRepository.save(load);
    }

    // 2
    @Override
    public List<LoadEntities> getAllLoads() {
        return loadRepository.findAll();
    }

    @Override
    public List<LoadEntities> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    // 3
    @Override
    public LoadEntities getLoadById(Long id) {
        return loadRepository.findById(id).orElse(null);
    }

    // 4
    @Override
public LoadEntities updateLoad(Long loadId, LoadEntities updatedLoad) {
    LoadEntities existingLoad = loadRepository.findById(loadId).orElse(null);
    if (existingLoad != null) {
        // Update other properties
        existingLoad.setLoadingPoint(updatedLoad.getLoadingPoint());
        existingLoad.setUnloadingPoint(updatedLoad.getUnloadingPoint());
        existingLoad.setProductType(updatedLoad.getProductType());
        existingLoad.setTruckType(updatedLoad.getTruckType());
        existingLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
        existingLoad.setWeight(updatedLoad.getWeight());
        existingLoad.setComment(updatedLoad.getComment());

        existingLoad.setDate(updatedLoad.getDate());
        return loadRepository.save(existingLoad);

        } else {
            return null;
        }
    }

    // 5
    @Override
    public void deleteLoadById(Long id) {
        loadRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
    loadRepository.deleteAll();
    }


}
