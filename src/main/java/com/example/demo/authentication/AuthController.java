package com.example.demo.authentication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authentication.jwt.JwtTokenProvider;
import com.example.demo.authentication.payload.request.LoginRequest;
import com.example.demo.authentication.payload.request.SignupRequest;
import com.example.demo.authentication.payload.response.JwtResponse;
import com.example.demo.authentication.payload.response.MessageResponse;
import com.example.demo.authentication.services.UserDetailsImpl;
import com.example.demo.role.ERole;
import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;


@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
	AuthenticationManager authenticationManager;
	
//	private AuthService authService;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtTokenProvider jwtProvider;
    
  
    @Operation(summary = "Sign in", description = "Sign in User")
    @SecurityRequirement(name = "bearerToken")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
        					 jwt, 
                             userDetails.getId(), 
                             userDetails.getUsername(), 
                             userDetails.getEmail(), 
                             roles));
      }
    
    
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
      if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Username is already taken!"));
      }
      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Email is already in use!"));
      }

      // Create new user's account
      User user = new User(signUpRequest.getUsername(), 
                 signUpRequest.getEmail(),
                 encoder.encode(signUpRequest.getPassword()));

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString())
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
      } else {
        strRoles.forEach(role -> {
          switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN.toString())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR.toString())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          }
        });
      }

      user.setRoles(roles);
      userRepository.save(user);

      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    
    
    
   
}