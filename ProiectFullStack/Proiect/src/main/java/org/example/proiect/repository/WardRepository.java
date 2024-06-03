package org.example.proiect.repository;

import org.example.proiect.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface WardRepository extends JpaRepository<Ward, Long> {
    Ward findByName(String name);
}
