package org.example.proiect.controller;

import org.example.proiect.model.LocalSupply;
import org.example.proiect.model.Ward;
import org.example.proiect.requests.LocalSupplyRequest;
import org.example.proiect.requests.DrugRequest;
import org.example.proiect.service.LocalSuplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localSupply")
public class LocalSupplyController {
    @Autowired
    private LocalSuplyService localSuplyService;

    @PostMapping("/addNewDrug/{wardName}")
    public ResponseEntity<String> addNewDrug(@PathVariable String wardName, @RequestBody DrugRequest drugRequest){
        return localSuplyService.addNewDrug(wardName, drugRequest);
    }

    @PostMapping("/addNewDrugList/{wardName}")
    public ResponseEntity<String> addNewDrugList(@PathVariable String wardName, @RequestBody List<DrugRequest> drugRequestList){
        return localSuplyService.addNewDrugList(wardName, drugRequestList);
    }


    @PutMapping("/addOneExistingDrug")
    public ResponseEntity<String> addLocalSupply(@RequestBody LocalSupplyRequest localSupplyRequest){
        return localSuplyService.addOneDrug(localSupplyRequest);
    }

    @PutMapping("/addManyExistingDrugs")
    public ResponseEntity<String> addManyDrugs(@RequestBody List<LocalSupplyRequest> drugModelList){
        return localSuplyService.addManyDrugs(drugModelList);
    }

    @PutMapping("/removeFromLocalSupply")
    public ResponseEntity<String> getFromLocalSupply(@RequestBody LocalSupplyRequest localSupplyRequest){
        return localSuplyService.getFromLocalSupply(localSupplyRequest);
    }

    @GetMapping("/getLocalSupply/{sectionName}")
    public ResponseEntity<Ward> getLocalSupply(@PathVariable String sectionName){
        return localSuplyService.getLocalSupply(sectionName);
    }


}
