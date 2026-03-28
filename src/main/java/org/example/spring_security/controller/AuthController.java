package org.example.spring_security.controller;

import lombok.Data;
import org.example.spring_security.Dto.LoginReqDto;
import org.example.spring_security.Dto.LoginResDTO;
import org.example.spring_security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
@Data
public class AuthController {
    private  final AuthService authService;

    @PutMapping("/login")
    public ResponseEntity<LoginResDTO> login(@RequestBody LoginReqDto loginReqDto){
        return ResponseEntity.ok( authService.login(loginReqDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody LoginReqDto signupDTO){
        if(authService.signup(signupDTO) != null){
            return ResponseEntity.ok("Sign Up Successful");
        }
        return ResponseEntity.badRequest().body("Failed Signup");
    }
}
