package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.LoginDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.exception.InvalidCredentialsException;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.repository.UserRepository;
import com.y2k.hospital.security.JwtUtils;
import com.y2k.hospital.security.TokenBlackList;
import com.y2k.hospital.service.interf.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenBlackList tokenBlackList;

    @Override
    public Response loginMedico(LoginDto loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFountException("Usuario no encontrado/Email Incorrecto"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Contraseña Incorrecta");
        }

        String token = jwtUtils.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .build();
    }

    @Override
    public Response loginAdmin(LoginDto loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFountException("Administrador no encontrado"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Contraseña Incorrecta");
        }

        String token = jwtUtils.generateToken(user);
        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .build();
    }

    @Override
    public Response loginEnfermero(LoginDto loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFountException("Enfermero no encontrado"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Contraseña Incorrecta");
        }

        String token = jwtUtils.generateToken(user);
        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .build();
    }

    @Override
    public Response loginPaciente(LoginDto loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFountException("Paciente no encontrado"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Contraseña Incorrecta");
        }

        String token = jwtUtils.generateToken(user);
        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .build();
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        if (token != null) {
            tokenBlackList.addToken(token);
            log.info("Token {} has been added to blacklist", token);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }


}
