package org.example.proiect.service;

import org.example.proiect.model.Ward;
import org.example.proiect.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {

    @Autowired
    private WardRepository wardRepository;

    public ResponseEntity<String> addWard(String name) {
        Ward ward = new  Ward();
        ward.setName(name);
        if(wardRepository.findByName(name) == null) {
            wardRepository.save(ward);
            return ResponseEntity.ok("Ward added successfully");
        }

        return ResponseEntity.badRequest().body("Ward already exists");
    }

    public ResponseEntity<String> deleteWard(String name) {
        Ward ward = wardRepository.findByName(name);
        if (ward == null) {
            return ResponseEntity.badRequest().body("Ward not found");
        }
        wardRepository.delete(ward);
        return ResponseEntity.ok("Ward deleted successfully");
    }

    public ResponseEntity<String> updateWard(String name, String newName) {
        Ward ward = wardRepository.findByName(name);
        if (ward == null) {
            return ResponseEntity.badRequest().body("Ward not found");
        }
        ward.setName(newName);
        wardRepository.save(ward);
        return ResponseEntity.ok("Ward updated successfully");
    }

    public ResponseEntity<String> getFromWard(String name) {
        Ward ward = wardRepository.findByName(name);
        if (ward == null) {
            return ResponseEntity.badRequest().body("Ward not found");
        }
        return ResponseEntity.ok("Ward found successfully");
    }

    public ResponseEntity<List<Ward>> getAllWards() {
        return ResponseEntity.ok(wardRepository.findAll());
    }
}