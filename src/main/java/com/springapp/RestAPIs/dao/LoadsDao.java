package com.springapp.RestAPIs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springapp.RestAPIs.entities.LoadsApi;


public interface LoadsDao extends JpaRepository <LoadsApi, String>{


    
} 