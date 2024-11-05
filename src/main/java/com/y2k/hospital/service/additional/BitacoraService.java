package com.y2k.hospital.service.additional;

import com.y2k.hospital.entity.Bitacora;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.repository.BitacoraRepository;
import com.y2k.hospital.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BitacoraService {

    private final UserRepository userRepository;
    private final BitacoraRepository bitacoraRepository;
    private final HttpServletRequest request;

    public void registrarAccion(String accion, User usuario) {
        String ipAddress = obtenerIpCliente();
        Bitacora bitacora = Bitacora.builder()
                .accion(accion)
                .fecha(LocalDate.now())
                .ip(ipAddress)
                .usuario(usuario)
                .build();
        bitacoraRepository.save(bitacora);
    }

    /*private String obtenerIpCliente() {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }*/

    private String obtenerIpCliente() {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        if ("0:0:0:0:0:0:0:1".equals(ipAddress)) { //Pasar de Ipv6 a Ipv4
            ipAddress = "127.0.0.1";
        }

        return ipAddress;
    }

    public User obtenerUsuarioActual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
