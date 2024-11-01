package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.EnfermeroDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Enfermero;
import com.y2k.hospital.entity.Role;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.EnfermeroRepository;
import com.y2k.hospital.repository.RoleRepository;
import com.y2k.hospital.repository.UserRepository;
import com.y2k.hospital.service.interf.EnfermeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnfermeroImpl implements EnfermeroService {

    private final UserRepository userRepository;
    private final EnfermeroRepository enfermeroRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerEnfermero(EnfermeroDto enfermeroDto) {
        Role enfermeroRole = roleRepository.findByName(Role.RolName.Enfermero)
                .orElseThrow(() -> new RuntimeException("Role Enfermero no encontrado"));

        User user = User.builder()
                .nombre(enfermeroDto.getName())
                .email(enfermeroDto.getEmail())
                .password(passwordEncoder.encode(enfermeroDto.getPassword()))
                .roles(Set.of(enfermeroRole))
                .build();

        User savedUser = userRepository.save(user);

        Enfermero enfermero = Enfermero.builder()
                .ci(enfermeroDto.getCi())
                .edad(enfermeroDto.getAge())
                .fechaNacimiento(enfermeroDto.getBirthDate())
                .user(savedUser)
                .build();

        Enfermero savedEnfermero = enfermeroRepository.save(enfermero);
        EnfermeroDto responseDto = entityDtoMapper.mapEnfermeroToDtoBasic(savedEnfermero);

        return Response.builder()
                .status(200)
                .message("Enfermero registrado exitosamente")
                .enfermero(responseDto)
                .build();
    }

    @Override
    public Response get() {
        List<Enfermero> enfermeros = enfermeroRepository.findAll();
        List<EnfermeroDto> enfermeroDtos = enfermeros.stream()
                .map(entityDtoMapper::mapEnfermeroToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .message("Lista de enfermeros obtenida exitosamente")
                .enfermeroList(enfermeroDtos)
                .build();
    }

    @Override
    public Response getByCi(Long ci) {
        Enfermero enfermero = enfermeroRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Enfermero no encontrado con CI: " + ci));

        EnfermeroDto enfermeroDto = entityDtoMapper.mapEnfermeroToDtoBasic(enfermero);

        return Response.builder()
                .status(200)
                .message("Enfermero encontrado")
                .enfermero(enfermeroDto)
                .build();
    }

    @Override
    public Response update(Long ci, EnfermeroDto enfermeroDto) {
        Enfermero enfermero = enfermeroRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Enfermero no encontrado con CI: " + ci));
        User user = enfermero.getUser();

        user.setNombre(enfermeroDto.getName());
        user.setEmail(enfermeroDto.getEmail());
        if (enfermeroDto.getPassword() != null && !enfermeroDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(enfermeroDto.getPassword()));
        }
        userRepository.save(user);

        enfermero.setEdad(enfermeroDto.getAge());
        enfermero.setFechaNacimiento(enfermeroDto.getBirthDate());

        Enfermero updatedEnfermero = enfermeroRepository.save(enfermero);
        EnfermeroDto responseDto = entityDtoMapper.mapEnfermeroToDtoBasic(updatedEnfermero);

        return Response.builder()
                .status(200)
                .message("Enfermero actualizado exitosamente")
                .enfermero(responseDto)
                .build();
    }

    @Override
    public Response delete(Long ci) {
        Enfermero enfermero = enfermeroRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Enfermero no encontrado con CI: " + ci));

        enfermeroRepository.delete(enfermero);
        userRepository.delete(enfermero.getUser());

        return Response.builder()
                .status(200)
                .message("Enfermero eliminado exitosamente")
                .build();
    }
}
