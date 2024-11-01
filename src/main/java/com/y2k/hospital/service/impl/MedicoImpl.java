package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.MedicoDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Especialidad;
import com.y2k.hospital.entity.Medico;
import com.y2k.hospital.entity.Role;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.EspecialidadRepository;
import com.y2k.hospital.repository.MedicoRepository;
import com.y2k.hospital.repository.RoleRepository;
import com.y2k.hospital.repository.UserRepository;
import com.y2k.hospital.service.interf.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoImpl implements MedicoService {

    private final UserRepository userRepository;
    private final MedicoRepository medicoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityDtoMapper entityDtoMapper;
    private final EspecialidadRepository especialidadRepository;

    @Override
    public Response registerMedico(MedicoDto medicoDto) {
        Role medicoRole = roleRepository.findByName(Role.RolName.Medico)
                .orElseThrow(() -> new RuntimeException("Role Médico no encontrado"));

        User user = User.builder()
                .nombre(medicoDto.getName())
                .email(medicoDto.getEmail())
                .password(passwordEncoder.encode(medicoDto.getPassword()))
                .roles(Set.of(medicoRole))
                .build();

        User savedUser = userRepository.save(user);

        Set<Especialidad> especialidades = medicoDto.getEspecialidades().stream()
                .map(nombre -> especialidadRepository.findByNombre(nombre)
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada: " + nombre)))
                .collect(Collectors.toSet());

        Medico medico = Medico.builder()
                .ci(medicoDto.getCi())
                .edad(medicoDto.getAge())
                .fechaNacimiento(medicoDto.getBirthDate())
                .user(savedUser)
                .especialidades(especialidades)
                .build();

        Medico savedMedico = medicoRepository.save(medico);

        MedicoDto responseDto = entityDtoMapper.mapMedicoToDtoBasic(savedMedico);

        return Response.builder()
                .status(200)
                .message("Médico registrado exitosamente")
                .medico(responseDto)
                .build();
    }

    @Override
    public Response get() {
        List<Medico> medicos = medicoRepository.findAll();
        List<MedicoDto> medicoDtos = medicos.stream()
                .map(entityDtoMapper::mapMedicoToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .message("Lista de médicos obtenida exitosamente")
                .medicoList(medicoDtos)
                .build();
    }

    @Override
    public Response getByCi(Long ci) {
        Medico medico = medicoRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con CI: " + ci));

        MedicoDto medicoDto = entityDtoMapper.mapMedicoToDtoBasic(medico);

        return Response.builder()
                .status(200)
                .message("Médico encontrado")
                .medico(medicoDto)
                .build();
    }


    @Override
    public Response update(Long ci, MedicoDto medicoDto) {
        Medico medico = medicoRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con CI: " + ci));
        User user = medico.getUser();

        user.setNombre(medicoDto.getName());
        user.setEmail(medicoDto.getEmail());
        if (medicoDto.getPassword() != null && !medicoDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(medicoDto.getPassword()));
        }
        userRepository.save(user);

        medico.setEdad(medicoDto.getAge());
        medico.setFechaNacimiento(medicoDto.getBirthDate());

        Set<Especialidad> especialidades = medicoDto.getEspecialidades().stream()
                .map(nombre -> especialidadRepository.findByNombre(nombre)
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada: " + nombre)))
                .collect(Collectors.toSet());
        medico.setEspecialidades(especialidades);

        Medico updatedMedico = medicoRepository.save(medico);
        MedicoDto responseDto = entityDtoMapper.mapMedicoToDtoBasic(updatedMedico);

        return Response.builder()
                .status(200)
                .message("Médico actualizado exitosamente")
                .medico(responseDto)
                .build();
    }

    @Override
    public Response delete(Long ci) {
        Medico medico = medicoRepository.findById(ci)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con CI: " + ci));

        medicoRepository.delete(medico);
        userRepository.delete(medico.getUser());

        return Response.builder()
                .status(200)
                .message("Médico eliminado exitosamente")
                .build();
    }



}
