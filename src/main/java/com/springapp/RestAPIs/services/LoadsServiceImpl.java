package com.springapp.RestAPIs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springapp.RestAPIs.entities.LoadsApi;


@Service
public class LoadsServiceImpl implements LoadsService {

    List<LoadsApi> list;
 
    
    public LoadsServiceImpl(){
        // list = new ArrayList<>();


    }
    
    // @Override
    // public List<PayLoad> addLoadEntities() {   
    //     throw new UnsupportedOperationException("Unimplemented method 'getLoadEntities'");
    // }
    
 
    
    @Override
    public List<LoadsApi> addPayLoad(LoadsApi payLoad) {
        list.add(payLoad);
        return list;
    }
}
