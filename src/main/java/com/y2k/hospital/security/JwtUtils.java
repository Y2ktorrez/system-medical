package com.y2k.hospital.security;

import com.y2k.hospital.entity.Enfermero;
import com.y2k.hospital.entity.Medico;
import com.y2k.hospital.entity.Paciente;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.repository.EnfermeroRepository;
import com.y2k.hospital.repository.MedicoRepository;
import com.y2k.hospital.repository.PacienteRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtUtils {

    private static final Long EXPIRATION_TIME_IN_MILLISEC = 1000L * 60L *60L *24L * 30L * 6L;
    private SecretKey key;
    @Autowired
    private EnfermeroRepository enfermeroRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Value("${secreteJwtString}")
    private String secreteJwtString;

    @PostConstruct
    private void init() {
        byte[] keyBytes = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(User user) {
        String username = user.getEmail();
        String nombre = user.getNombre();
        Long ci = getCiFromUser(user);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", user.getRoles().stream().map(role -> "ROLE_" + role.getName().name()).collect(Collectors.toList())) // Incluye roles con prefijo
                .claim("ci", ci)
                .claim("nombre", nombre)
                .claim("email", username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISEC))
                .signWith(key)
                .compact();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISEC))
                .signWith(key)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    private Long getCiFromUser(User user) {
        // Buscamos el CI de las entidades derivadas usando el User
        Optional<Enfermero> enfermero = enfermeroRepository.findByUser(user);
        if (enfermero.isPresent()) {
            return enfermero.get().getCi();
        }

        Optional<Medico> medico = medicoRepository.findByUser(user);
        if (medico.isPresent()) {
            return medico.get().getCi();
        }

        Optional<Paciente> paciente = pacienteRepository.findByUser(user);
        if (paciente.isPresent()) {
            return paciente.get().getCi();
        }

        // Si no encontramos el CI en ninguna de las entidades, lanzamos una excepción.
        //throw new IllegalArgumentException("No se encontró el CI para el usuario con ID: " + user.getId());
        return 0L;
    }

}
