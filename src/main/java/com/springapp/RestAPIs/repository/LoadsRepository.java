package com.springapp.RestAPIs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapp.RestAPIs.entities.LoadsApi;

import java.util.List;

@Repository
public interface LoadsRepository extends JpaRepository<LoadsApi, Long> {

    List<LoadsApi> findByShipperId(String shipperId);

    // Spring Data JPA provides the findById method by default

    // Deleting a load by ID
    void deleteById(Long loadId);

    // Optionally, you can add more custom methods based on your needs
    // For example:
    // List<Load> findByUnloadingPointAndProductType(String unloadingPoint, String productType);
}
