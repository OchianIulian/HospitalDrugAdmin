package org.example.proiect.repository;

import org.example.proiect.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugsStorageRepository extends JpaRepository<Drug, Long> {
    Drug findByName(String name);
}
