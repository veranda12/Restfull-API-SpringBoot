package com.TicketingSystem.TicketingSystem.controllers;

import com.TicketingSystem.TicketingSystem.dtos.UserDtoSecurity.RegistrationDto;
import com.TicketingSystem.TicketingSystem.services.AuthService;
import com.TicketingSystem.TicketingSystem.services.MyUserDetailsServices;
import com.TicketingSystem.TicketingSystem.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private AuthService service;
    private MyUserDetailsServices myUserDetailsServices;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService service, MyUserDetailsServices myUserDetailsServices, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.service = service;
        this.myUserDetailsServices = myUserDetailsServices;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @GetMapping("admin")
    public String adminPanel() {
        return "<h1>Admin section</h1>";
    }

    @PostMapping("register")
    public Boolean registration(@RequestParam String userId, @RequestBody RegistrationDto registrant){
        return service.registration(userId,registrant);
    }

    @PostMapping("jwt")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody RegistrationDto credentials){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword()

        ));

        UserDetails userDetails = myUserDetailsServices.loadUserByUsername(credentials.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}
