package org.example.spring_security.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_security.Dto.LoginReqDto;
import org.example.spring_security.Dto.LoginResDTO;
import org.example.spring_security.config.AppUtil;
import org.example.spring_security.entity.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private  final AppUtil appUtil;

    public LoginResDTO login(LoginReqDto loginReqDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqDto.getUserName(),loginReqDto.getPassword())
        );
        Users user = (Users) authentication.getPrincipal();
        String token = appUtil.generateToken(user);
        return new LoginResDTO(token,user.getId());
    }
}
