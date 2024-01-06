package com.springapp.RestAPIs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.RestAPIs.entities.Load;
import com.springapp.RestAPIs.repository.LoadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private final LoadRepository loadRepository;

    
    public LoadServiceImpl(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    @Override
    public List<Load> getAllLoads() {
        return loadRepository.findAll();
    }

    @Override
    public List<Load> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    @Override
    public Load getLoadById(Long loadId) {
        Optional<Load> load = loadRepository.findById(loadId);
        return load.orElse(null);
    }

    @Override
    public Load saveLoad(Load load) {
        return loadRepository.save(load);
    }

    @Override
    public void deleteLoadById(Long loadId) {
        loadRepository.deleteById(loadId);
    }

    

    @Override
    public void deleteAll() {
        
        loadRepository.deleteAll();
    }

}
