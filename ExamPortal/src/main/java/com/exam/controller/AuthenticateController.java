package com.exam.controller;

import com.exam.configuration.JwtUtils;
import com.exam.domain.JwtRequest;
import com.exam.domain.JwtResponse;
import com.exam.domain.Users;
import com.exam.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("USER NOT FOUND !!");
        }catch (Exception e){
            e.printStackTrace();
        }
       UserDetails userDetails =  userDetailsService.loadUserByUsername(jwtRequest.getUsername());
       String token =  jwtUtils.generateToken(userDetails);
      return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( username,password));
        }catch (DisabledException disabledException){
            disabledException.printStackTrace();
            throw new Exception("USER DISABLED");
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Invalid credential" +e.getMessage());
        }
    }
//   todo: return the details of current user
    @GetMapping("/current-user")
    public Users  getCurrentUser(Principal principal){
        return (Users) userDetailsService.loadUserByUsername(principal.getName());
    }
}
