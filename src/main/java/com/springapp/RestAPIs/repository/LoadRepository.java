package com.springapp.RestAPIs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springapp.RestAPIs.entities.Load;
import java.util.List;

@Repository 
public interface LoadRepository extends JpaRepository<Load, Long> {
    // Retrieve loads by shipperId
    List<Load> findByShipperId(String shipperId);
    
}