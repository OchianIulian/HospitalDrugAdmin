package org.example.proiect.controller;

import jakarta.websocket.server.PathParam;
import org.example.proiect.model.Ward;
import org.example.proiect.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ward")
public class WardController {
    @Autowired
    private WardService wardService;

    @PostMapping("/add/{name}")
    public ResponseEntity<String> addWard(@PathVariable String name) {
        return wardService.addWard(name);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteWard(@PathVariable String name) {
        return wardService.deleteWard(name);
    }

    @PutMapping("/update/{name}/{newName}")
    public void updateWard(@RequestParam String name, @RequestParam String newName) {
        wardService.updateWard(name, newName);
    }

    @GetMapping("/get/{name}")
    public void getFromWard(@RequestParam String name) {
        wardService.getFromWard(name);
    }

    @GetMapping("/getAllWards")
    public ResponseEntity<List<Ward>> getAllWards() {
        return wardService.getAllWards();
    }

}
