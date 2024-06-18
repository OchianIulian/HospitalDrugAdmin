package org.example.proiect.repository;

import org.example.proiect.model.LocalSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalSuplyRepository extends JpaRepository<LocalSupply, Long> {
    @Query("SELECT l FROM LocalSupply l WHERE l.drugName = ?1 AND l.ward.name = ?2")
    LocalSupply findByDrugName(String drugName, String wardName);

    @Query("SELECT l FROM LocalSupply l WHERE l.ward.name = ?1")
    List<LocalSupply> findByWardName(String sectionName);
}
