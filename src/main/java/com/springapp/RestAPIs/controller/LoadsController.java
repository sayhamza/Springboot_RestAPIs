package com.springapp.RestAPIs.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springapp.RestAPIs.entities.LoadsApi;
import com.springapp.RestAPIs.services.LoadsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class LoadsController {

    @Autowired
    private LoadsService loadservice;

    @GetMapping("/hello")
    public String hello(@RequestParam String param) {
        return "This is Hello";
    }
    
    // @GetMapping("/load")
    // public LoadsApi getPayLoad(@RequestParam String param) {
    //     return ;
    // }

     

    @PostMapping("/load")
    public List<LoadsApi> addPayload(@RequestBody List<LoadsApi> payLoad) {
        return this.loadservice.addPayLoad(null);
    }
    

}
