package org.example.proiect.service;

import org.example.proiect.model.Drug;
import org.example.proiect.model.Ward;
import org.example.proiect.repository.DrugsStorageRepository;
import org.example.proiect.repository.WardRepository;
import org.example.proiect.requests.LocalSupplyRequest;
import org.example.proiect.model.LocalSupply;
import org.example.proiect.repository.LocalSuplyRepository;
import org.example.proiect.requests.DrugRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalSuplyService {
    @Autowired
    private LocalSuplyRepository localSuplyRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DrugsStorageRepository drugsStorageRepository;

    public ResponseEntity<String> addOneDrug(LocalSupplyRequest localSupplyRequest) {
        LocalSupply localSupply = localSuplyRepository.findByDrugName(localSupplyRequest.getDrugName(), localSupplyRequest.getWardName()) ;
        if(localSupply ==null){
            return ResponseEntity.badRequest().body("Drug not found in local supply");
        }

        Drug drug = drugsStorageRepository.findByName(localSupplyRequest.getDrugName());
        if(drug == null){
            return ResponseEntity.badRequest().body("Drug not found in global drug storage");
        }

        if(localSupplyRequest.getQuantity() > drug.getQuantity()){
            return ResponseEntity.badRequest().body("Not enough quantity in storage");
        }

        localSupply.setQuantity(localSupply.getQuantity() + localSupplyRequest.getQuantity());
        localSuplyRepository.save(localSupply);
        return ResponseEntity.badRequest().body("Drug already exists");
    }

    public ResponseEntity<String> addManyDrugs(List<LocalSupplyRequest> drugModelList) {
        for(LocalSupplyRequest localSupplyRequest : drugModelList){
            LocalSupply localSupply = localSuplyRepository.findByDrugName(localSupplyRequest.getDrugName(), localSupplyRequest.getWardName()) ;
            if(localSupply ==null){
                return ResponseEntity.badRequest().body("Drug " + localSupplyRequest.getDrugName() + "not found in local supply");
            }

            Drug drug = drugsStorageRepository.findByName(localSupplyRequest.getDrugName());
            if(drug == null){
                return ResponseEntity.badRequest().body("Drug " + localSupplyRequest.getDrugName() + "not found in global drug storage");
            }

            if(localSupplyRequest.getQuantity() > drug.getQuantity()){
                return ResponseEntity.badRequest().body("Not enough quantity in storage");
            }

            localSupply.setQuantity(localSupply.getQuantity() + localSupplyRequest.getQuantity());
            localSuplyRepository.save(localSupply);
        }
        return ResponseEntity.badRequest().body("Drug already exists");
    }


    public ResponseEntity<String> getFromLocalSupply(LocalSupplyRequest localSupplyRequest) {
        LocalSupply localSupply = localSuplyRepository.findByDrugName(localSupplyRequest.getDrugName(), localSupplyRequest.getWardName());
        if(localSupply ==null){
            return ResponseEntity.badRequest().body("Drug not found in local supply");
        }
        if(localSupply.getQuantity() < localSupply.getQuantity()){
            return ResponseEntity.badRequest().body("Not enough quantity in storage");
        }
        localSupply.setQuantity(localSupply.getQuantity() - localSupplyRequest.getQuantity());
        localSuplyRepository.save(localSupply);
        return ResponseEntity.ok().body("Drug consumed successfully");
    }

    public ResponseEntity<String> addNewDrug(String wardName, DrugRequest drugRequest) {
        LocalSupply localSupply = localSuplyRepository.findByDrugName(drugRequest.getName(), wardName);
        if(localSupply !=null){
            return ResponseEntity.badRequest().body("Drug already exists");
        }
        else{
            Ward ward = wardRepository.findByName(wardName);
            if(ward == null){
                return ResponseEntity.badRequest().body("Ward not found");
            }

            Drug drug = drugsStorageRepository.findByName(drugRequest.getName());
            if(drug == null){
                return ResponseEntity.badRequest().body("Drug not found in global drug storage");
            }

            if(drugRequest.getQuantity() > drug.getQuantity()){
                return ResponseEntity.badRequest().body("Not enough quantity in storage");
            }

            LocalSupply newLocalSupply = new LocalSupply();
            newLocalSupply.setDrugName(drugRequest.getName());
            newLocalSupply.setManufacturer(drugRequest.getManufacturer());
            newLocalSupply.setDescription(drugRequest.getDescription());
            newLocalSupply.setSideEffects(drugRequest.getSideEffects());
            newLocalSupply.setDosage(drugRequest.getDosage());
            newLocalSupply.setQuantity(drugRequest.getQuantity());
            newLocalSupply.setWard(ward);
            localSuplyRepository.save(newLocalSupply);


            drug.setQuantity(drug.getQuantity() - drugRequest.getQuantity());
            drugsStorageRepository.save(drug);

            return ResponseEntity.ok("Drug added successfully");
        }
    }

    public ResponseEntity<String> addNewDrugList(String wardName, List<DrugRequest> drugRequestList) {
        for (DrugRequest drugRequest: drugRequestList){
            addNewDrug(wardName, drugRequest);
        }
        return ResponseEntity.ok("Drugs added successfully");
    }

    public ResponseEntity<Ward> getLocalSupply(String sectionName) {
//        List<LocalSupply> localSupply = localSuplyRepository.findByWardName(sectionName);
//        if(localSupply == null){
//            return ResponseEntity.badRequest().body(null);
//        }

        Ward ward = wardRepository.findByName(sectionName);
        if(ward == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(ward);
        //return ResponseEntity.ok(localSupply);
    }
}
