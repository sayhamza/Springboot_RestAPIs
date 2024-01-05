package com.springapp.RestAPIs.repository;

import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapp.RestAPIs.entities.Load;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {

    List<Load> findByShipperId(UUID shipperId);

    // Spring Data JPA provides the findById method by default

    // Deleting a load by ID
    void deleteById(Long loadId);

    // Optionally, you can add more custom methods based on your needs
    // For example:
    // List<Load> findByUnloadingPointAndProductType(String unloadingPoint, String productType);
}
