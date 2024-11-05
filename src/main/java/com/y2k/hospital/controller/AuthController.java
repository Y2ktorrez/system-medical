package com.y2k.hospital.controller;

import com.y2k.hospital.dto.LoginDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin")
    public ResponseEntity<Response> loginAdmin(@RequestBody LoginDto loginRequest) {
        Response response = authService.loginAdmin(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/medico")
    public ResponseEntity<Response> loginMedico(@RequestBody LoginDto loginRequest) {
        Response response = authService.loginMedico(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/enfermero")
    public ResponseEntity<Response> loginEnfermero(@RequestBody LoginDto loginRequest) {
        Response response = authService.loginEnfermero(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/paciente")
    public ResponseEntity<Response> loginPaciente(@RequestBody LoginDto loginRequest) {
        Response response = authService.loginPaciente(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Logout successful");
    }

}
