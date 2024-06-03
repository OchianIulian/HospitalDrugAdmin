package org.example.proiect.controller;

import org.example.proiect.model.Drug;
import org.example.proiect.requests.DrugRequest;
import org.example.proiect.service.DrugStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugStorage")
public class DrugStorageController {

    @Autowired
    private DrugStorageService drugStorageService;

    @PostMapping("/addDrug")
    public ResponseEntity<String> addDrug(@RequestBody DrugRequest request) {
        return drugStorageService.addDrug(request);
    }

    @DeleteMapping("/deleteDrug/{name}")
    public ResponseEntity<String> deleteDrug(@PathVariable String name) {
        return drugStorageService.deleteDrug(name);
    }

    @PutMapping("/addDrugQuantity/{name}/{quantity}")
    public ResponseEntity<String> updateDrug(@PathVariable String name, @PathVariable Long quantity) {
        return drugStorageService.updateDrug(name, quantity);
    }

    @PutMapping("/getFromDrug/{name}/{quantity}")
    public ResponseEntity<String> getFromDrug(@PathVariable String name, @PathVariable Long quantity) {
        return drugStorageService.getFromDrug(name, quantity);
    }

    @GetMapping("/getDrug/{name}")
    public ResponseEntity<String> getDrug(@PathVariable String name) {
        return drugStorageService.getDrug(name);
    }

    @GetMapping("/getAllDrugs")
    public ResponseEntity<List<Drug>> getAllDrugs() {
        return drugStorageService.getAllDrugs();
    }
}
