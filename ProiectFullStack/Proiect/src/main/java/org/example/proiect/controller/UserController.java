package org.example.proiect.controller;

import org.example.proiect.model.User;
import org.example.proiect.requests.UserRequest;
import org.example.proiect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserRequest request) {
        return userService.addUser(request);
    }

    @DeleteMapping("/deleteUser/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        return userService.deleteUser(name);
    }

    @PutMapping("/updateUser/{name}")
    public ResponseEntity<String> updateUser(@PathVariable String name, @RequestBody UserRequest request) {
        return userService.updateUser(name, request);
    }

    @GetMapping("/getUser/{name}")
    public ResponseEntity<String> getUser(@PathVariable String name) {
        return userService.getUser(name);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getUsers();
    }

}
