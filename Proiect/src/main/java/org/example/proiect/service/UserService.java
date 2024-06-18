package org.example.proiect.service;

import org.example.proiect.model.User;
import org.example.proiect.repository.UserRepository;
import org.example.proiect.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> addUser(UserRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setCnp(request.getCnp());
        user.setRole(request.getRole());

        User checkUser = userRepository.findByFullName(request.getFullName());
        if (checkUser != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        userRepository.save(user);

        return ResponseEntity.ok("User added successfully");
    }

    public ResponseEntity<String> deleteUser(String name) {
        User user = userRepository.findByFullName(name);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        userRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    public ResponseEntity<String> updateUser(String name, UserRequest request) {
        User user = userRepository.findByFullName(name);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        user.setEmail(request.getEmail());
        user.setCnp(request.getCnp());
        user.setRole(request.getRole());

        userRepository.save(user);
        return ResponseEntity.ok("User updated successfully");
    }

    public ResponseEntity<String> getUser(String name) {
        User user = userRepository.findByFullName(name);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(user.toString());
    }

    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
