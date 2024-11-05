package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.LoginDto;
import com.y2k.hospital.dto.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;

public interface AuthService {
    Response loginMedico(LoginDto loginRequest);
    Response loginAdmin(LoginDto loginRequest);
    Response loginEnfermero(LoginDto loginRequest);
    Response loginPaciente(LoginDto loginRequest);
    void logout(HttpServletRequest request);
}
