package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.FichaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Especialidad;
import com.y2k.hospital.entity.Ficha;
import com.y2k.hospital.entity.Medico;
import com.y2k.hospital.entity.Paciente;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.EspecialidadRepository;
import com.y2k.hospital.repository.FichaRepository;
import com.y2k.hospital.repository.MedicoRepository;
import com.y2k.hospital.repository.PacienteRepository;
import com.y2k.hospital.service.interf.FichaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FichaImpl implements FichaService {
    private final FichaRepository fichaRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final EspecialidadRepository especialidadRepository;

    @Override
    public Response createFicha(FichaDto fichaDto){
        Medico medico = medicoRepository.findById(fichaDto.getCi_medico())
                .orElseThrow(() -> new NotFountException("Medico no encontrado con CI: " + fichaDto.getCi_medico()));

        Paciente paciente = pacienteRepository.findById(fichaDto.getCi_paciente())
                .orElseThrow(() -> new NotFountException("Paciente no encontrado con CI: " + fichaDto.getCi_paciente()));

        Especialidad especialidad = especialidadRepository.findById(fichaDto.getId_especialidad())
                .orElseThrow(() -> new NotFountException("Especialidad no encontrada con ID: " + fichaDto.getId_especialidad()));

        Ficha ficha=Ficha.builder()
                .fechaAtencion(fichaDto.getFechaAtencion())
                .fechaEmision(fichaDto.getFechaEmision())
                .horaAtencion(fichaDto.getHoraAtencion())
                .medico(medico)
                .paciente(paciente)
                .especialidad(especialidad)
                .build();

        Ficha fichaGuardada= fichaRepository.save(ficha);

        FichaDto responseDto = entityDtoMapper.mapFichaToDtoBasic(fichaGuardada);

        return Response.builder()
                .status(200)
                .message("Ficha registrada exitosamente")
                .ficha(responseDto)
                .build();
    }

    @Override
    public Response getFichaById(Long id){
        Ficha ficha = fichaRepository.findById(id)
                .orElseThrow(()->new NotFountException("Ficha no encontrada con ID: "+id));

        FichaDto fichaDto= entityDtoMapper.mapFichaToDtoBasic(ficha);

        return Response.builder()
                .status(200)
                .message("Ficha encontrada")
                .ficha(fichaDto)
                .build();
    }

    @Override
    public Response getAllFichas(){
        List<FichaDto> fichas = fichaRepository.findAll().stream()
                .map(entityDtoMapper::mapFichaToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de fichas obtenida")
                .fichaList(fichas)
                .build();
    }

    @Override
    public Response updateFicha(Long id, FichaDto fichaDto){
        Ficha ficha = fichaRepository.findById(id)
                .orElseThrow(()->new NotFountException("Ficha no encontrada con ID: "+id));

        if (fichaDto.getFechaEmision() != null) ficha.setFechaEmision(fichaDto.getFechaEmision());


        if (fichaDto.getFechaAtencion() != null)  ficha.setFechaAtencion(fichaDto.getFechaAtencion());


        if (fichaDto.getHoraAtencion() != null)  ficha.setHoraAtencion(fichaDto.getHoraAtencion());


        Ficha fichaActualizada = fichaRepository.save(ficha);
        FichaDto responseDto = entityDtoMapper.mapFichaToDtoBasic(fichaActualizada);

        return Response.builder()
                .status(200)
                .message("Ficha Actualizada exitosamente")
                .ficha(responseDto)
                .build();
    }

    @Override
    public Response deleteFicha(Long id){
        Ficha ficha = fichaRepository.findById(id)
                .orElseThrow(()->new NotFountException("Ficha no encontrada con ID: "+id));

        fichaRepository.delete(ficha);

        return Response.builder()
                .status(200)
                .message("Ficha eliminada exitosamente")
                .build();
    }
}
