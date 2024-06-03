package org.example.proiect.service;


import lombok.RequiredArgsConstructor;
import org.example.proiect.enums.Roles;
import org.example.proiect.model.User;
import org.example.proiect.repository.UserRepository;
import org.example.proiect.requests.AuthenticationRequest;
import org.example.proiect.requests.RegisterRequest;
import org.example.proiect.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Transactional
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = null;
        if(request.getRole() == Roles.USER){
            user = authenticate_user(request);
        } else if(request.getRole() == Roles.ADMIN){
            user = authenticate_admin(request);
        } else {
            throw new RuntimeException("Must add Role");
        }
        var checkUser= repository.findByEmail(request.getEmail());
        //check if the user is already registered with this email
        if(checkUser.isPresent() && !user.isEnabled()){
            // resend the email if the user exists and the email is not confirmed
            System.out.println("We sent you an email to activate your account.");
        } else if(checkUser.isPresent() && user.isEnabled()){
            throw new RuntimeException("This user with this email already exists");
        }

        if(checkUser.isEmpty())
            repository.save(user);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        var jwtToken =  service.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }





    private User authenticate_admin(RegisterRequest request) {
        //todo: De trimis mie un email de confirmare ca sa poata deveni admin
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .cnp(passwordEncoder.encode(request.getCnp()))
                .role(Roles.ADMIN)
                .build();
    }

    private User authenticate_user(RegisterRequest request){
        //todo: de trimis email de confirmare pe mailul utilizatorului
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .cnp(passwordEncoder.encode(request.getCnp()))
                .role(Roles.USER)
                .build();
    }

    public ResponseEntity<String> authenticate(AuthenticationRequest request) {
        System.out.println("Authenticating user");
        var user= repository.findByEmail(request.getEmail());
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getCnp())
        );
        System.out.println("User authenticated");

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.get().getRole());
        var jwtToken =  service.generateToken(claims, user.get());
        System.out.println("Token generated");
//        return ResponseEntity.ok(AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build().toString());

        return ResponseEntity.ok(jwtToken);
    }

}
