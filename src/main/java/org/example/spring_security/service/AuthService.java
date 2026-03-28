package org.example.spring_security.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_security.Dto.LoginReqDto;
import org.example.spring_security.Dto.LoginResDTO;
import org.example.spring_security.config.AppUtil;
import org.example.spring_security.entity.Users;
import org.example.spring_security.repository.UsersRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private  final AppUtil appUtil;
    private  final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;

    public LoginResDTO login(LoginReqDto loginReqDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqDto.getUserName(),loginReqDto.getPassword())
        );
        Users user = (Users) authentication.getPrincipal();
        String token = appUtil.generateToken(user);
        return new LoginResDTO(token,user.getId());
    }
    public String signup(LoginReqDto singUpDTO) {

        Optional<Users> existingUser = usersRepo.findByUserName(singUpDTO.getUserName());

        if(existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        Users newUser = Users.builder()
                .userName(singUpDTO.getUserName())
                .password(passwordEncoder.encode( singUpDTO.getPassword()))
                .build();

        usersRepo.save(newUser);

        return "User registered successfully";
    }
}
