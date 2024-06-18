package org.example.proiect.controller;


import lombok.RequiredArgsConstructor;
import org.example.proiect.requests.AuthenticationRequest;
import org.example.proiect.requests.RegisterRequest;
import org.example.proiect.responses.AuthenticationResponse;
import org.example.proiect.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * In aceasta clasa se gasesc endpointurile pentru authentificarea si inregistrarea utilizatorilor
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate (
            @RequestBody AuthenticationRequest request
    ){
        return service.authenticate(request);
    }

}
