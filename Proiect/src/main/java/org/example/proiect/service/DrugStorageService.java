package org.example.proiect.service;

import org.example.proiect.model.Drug;
import org.example.proiect.repository.DrugsStorageRepository;
import org.example.proiect.requests.DrugRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugStorageService {

    @Autowired
    private DrugsStorageRepository drugStorageRepository;

    public ResponseEntity<String> addDrug(DrugRequest request) {
        Drug drug = new Drug();
        drug.setName(request.getName());
        drug.setManufacturer(request.getManufacturer());
        drug.setDescription(request.getDescription());
        drug.setSideEffects(request.getSideEffects());
        drug.setDosage(request.getDosage());
        drug.setQuantity(request.getQuantity());

        if(drugStorageRepository.findByName(request.getName()) != null) {
            return ResponseEntity.badRequest().body("Drug already exists");
        }

        drugStorageRepository.save(drug);
        return ResponseEntity.ok("Drug added successfully");
    }

    public ResponseEntity<String> deleteDrug(String name) {
        Drug drug = drugStorageRepository.findByName(name);
        if (drug == null) {
            return ResponseEntity.badRequest().body("Drug not found");
        }
        drugStorageRepository.delete(drug);
        return ResponseEntity.ok("Drug deleted successfully");
    }

    public ResponseEntity<String> updateDrug(String name, Long quantity) {
        Drug drug = drugStorageRepository.findByName(name);
        if (drug == null) {
            return ResponseEntity.badRequest().body("Drug not found");
        }
        drug.setQuantity(drug.getQuantity() + quantity);
        drugStorageRepository.save(drug);
        return ResponseEntity.ok("Drug updated successfully");
    }

    public ResponseEntity<String> getFromDrug(String name, Long quantity) {
        Drug drug = drugStorageRepository.findByName(name);
        if (drug == null) {
            return ResponseEntity.badRequest().body("Drug not found");
        }
        if (drug.getQuantity() < quantity) {
            return ResponseEntity.badRequest().body("Not enough quantity");
        }
        drug.setQuantity(drug.getQuantity() - quantity);
        drugStorageRepository.save(drug);
        return ResponseEntity.ok("Drug quantity updated successfully");
    }

    public ResponseEntity<String> getDrug(String name) {
        Drug drug = drugStorageRepository.findByName(name);
        if (drug == null) {
            return ResponseEntity.badRequest().body("Drug not found");
        }
        return ResponseEntity.ok(drug.toString());
    }

    public ResponseEntity<List<Drug>> getAllDrugs() {
        return ResponseEntity.ok(drugStorageRepository.findAll());
    }
}
