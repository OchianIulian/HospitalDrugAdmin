package org.example.proiect.utils;

import lombok.RequiredArgsConstructor;
import org.example.proiect.dao.DrugsStorageDao;
import org.example.proiect.dao.LocalSupplyDao;
import org.example.proiect.dao.UserDao;
import org.example.proiect.dao.WardDao;
import org.example.proiect.model.Drug;
import org.example.proiect.model.LocalSupply;
import org.example.proiect.model.User;
import org.example.proiect.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Utils {
    @Autowired
    DrugsStorageDao drugsStorageDao;
    @Autowired
    LocalSupplyDao localSupplyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    WardDao wardDao;

    public void Util(){
        Drug drug = drugsStorageDao.findByName("Paracetamol");
        drugsStorageDao.save(drug);
        LocalSupply localSupply = localSupplyDao.findByDrugNameAndWardName("Paracetamol", "Cardiology");
        localSupplyDao.save(localSupply);
        User user = userDao.findByFullName("John Doe");
        userDao.save(user);
        wardDao.save(wardDao.findByName("Cardiology"));
        Ward ward = wardDao.findByName("Cardiology");

    }
}
