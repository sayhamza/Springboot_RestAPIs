package com.springapp.RestAPIs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springapp.RestAPIs.entities.LoadEntities;
import java.util.List;

@Repository 
public interface LoadRepository extends JpaRepository<LoadEntities, Long> {
    // Retrieve loads by shipperId
    List<LoadEntities> findByShipperId(String shipperId);
}