package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.EspecialidadDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Especialidad;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.EspecialidadRepository;
import com.y2k.hospital.service.interf.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createEspecialidad(EspecialidadDto especialidadDto) {
        Especialidad especialidad = Especialidad.builder()
                .nombre(especialidadDto.getNombre())
                .descripcion(especialidadDto.getDescripcion())
                .build();

        Especialidad especialidadGuardada = especialidadRepository.save(especialidad);

        EspecialidadDto responseDto = entityDtoMapper.mapEspecialidadToDtoBasic(especialidadGuardada);

        return Response.builder()
                .status(200)
                .message("Especialidad registrada exitosamente")
                .especialidad(responseDto)
                .build();
    }

    @Override
    public Response getEspecialidadById(Long id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Especialidad no encontrada con ID: " + id));

        EspecialidadDto responseDto = entityDtoMapper.mapEspecialidadToDtoBasic(especialidad);

        return Response.builder()
                .status(200)
                .message("Especialidad encontrada")
                .especialidad(responseDto)
                .build();
    }

    @Override
    public Response getAllEspecialidades() {
        List<EspecialidadDto> especialidades = especialidadRepository.findAll().stream()
                .map(entityDtoMapper::mapEspecialidadToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .message("Lista de especialidades obtenida")
                .especialidadList(especialidades)
                .build();
    }

    @Override
    public Response updateEspecialidad(Long id, EspecialidadDto especialidadDto) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Especialidad no encontrada con ID: " + id));

        especialidad.setNombre(especialidadDto.getNombre());
        especialidad.setDescripcion(especialidadDto.getDescripcion());

        Especialidad especialidadActualizada = especialidadRepository.save(especialidad);
        EspecialidadDto responseDto = entityDtoMapper.mapEspecialidadToDtoBasic(especialidadActualizada);

        return Response.builder()
                .status(200)
                .message("Especialidad actualizada exitosamente")
                .especialidad(responseDto)
                .build();
    }

    @Override
    public Response deleteEspecialidad(Long id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Especialidad no encontrada con ID: " + id));

        especialidadRepository.delete(especialidad);

        return Response.builder()
                .status(200)
                .message("Especialidad eliminada exitosamente")
                .build();
    }
}
