package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.PacienteDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Paciente;
import com.y2k.hospital.entity.Role;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.PacienteRepository;
import com.y2k.hospital.repository.RoleRepository;
import com.y2k.hospital.repository.UserRepository;
import com.y2k.hospital.service.interf.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteImpl implements PacienteService {

    private final UserRepository userRepository;
    private final PacienteRepository pacienteRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerPaciente(PacienteDto pacienteDto) {
        Role pacienteRole = roleRepository.findByName(Role.RolName.Paciente)
                .orElseThrow(() -> new RuntimeException("Role Paciente no encontrado"));

        User user = User.builder()
                .nombre(pacienteDto.getName())
                .email(pacienteDto.getEmail())
                .password(passwordEncoder.encode(pacienteDto.getPassword()))
                .roles(Set.of(pacienteRole))
                .build();

        User savedUser = userRepository.save(user);

        Paciente paciente = Paciente.builder()
                .ci(pacienteDto.getCi())
                .fechaNacimiento(pacienteDto.getBirthDate())
                .user(savedUser)
                .build();

        Paciente savedPaciente = pacienteRepository.save(paciente);
        PacienteDto responseDto = entityDtoMapper.mapPacienteToDtoBasic(savedPaciente);

        return Response.builder()
                .status(200)
                .message("Paciente registrado exitosamente")
                .paciente(responseDto)
                .build();
    }

    @Override
    public Response getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDto> pacienteDtos = pacientes.stream()
                .map(entityDtoMapper::mapPacienteToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .message("Lista de pacientes obtenida exitosamente")
                .pacienteList(pacienteDtos)
                .build();
    }

    @Override
    public Response getByCi(Long ci) {
        Paciente paciente = pacienteRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con CI: " + ci));

        PacienteDto pacienteDto = entityDtoMapper.mapPacienteToDtoBasic(paciente);

        return Response.builder()
                .status(200)
                .message("Paciente encontrado")
                .paciente(pacienteDto)
                .build();
    }
}
