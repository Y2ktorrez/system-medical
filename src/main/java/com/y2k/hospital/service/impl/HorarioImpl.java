package com.y2k.hospital.service.impl;

import com.y2k.hospital.Enum.Dia;
import com.y2k.hospital.dto.HorarioDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Horario;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.HorarioRepository;
import com.y2k.hospital.service.interf.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HorarioImpl implements HorarioService {
    private final HorarioRepository horarioRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createHorario(HorarioDto horarioDto){
        if (!horarioDto.getHoraInicio().isBefore(horarioDto.getHoraFin())) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin.");
        }

        boolean horarioDuplicado = horarioRepository.existsByDaysAndHoraInicioAndHoraFin(
                Dia.valueOf(horarioDto.getDia()),
                horarioDto.getHoraInicio(),
                horarioDto.getHoraFin()
        );

        if (horarioDuplicado) throw new IllegalArgumentException("Ya existe un horario con el mismo día y horas especificadas.");


        Horario horario=Horario.builder()
                .days(Dia.valueOf(horarioDto.getDia()))
                .horaInicio(horarioDto.getHoraInicio())
                .horaFin(horarioDto.getHoraFin())
                .build();

        Horario horarioGuardado=horarioRepository.save(horario);

        HorarioDto responseDto = entityDtoMapper.mapHorarioToDtoBasic(horarioGuardado);

        return Response.builder()
                .status(200)
                .message("Horario registrado exitosamente")
                .horario(responseDto)
                .build();
    }

    @Override
    public Response getHorarioById(Long id){
        Horario horario=horarioRepository.findById(id)
                .orElseThrow(()->new NotFountException("Horario no encontrada con ID: "+id));

        HorarioDto horarioDto=entityDtoMapper.mapHorarioToDtoBasic(horario);

        return Response.builder()
                .status(200)
                .message("Horario encontrado")
                .horario(horarioDto)
                .build();
    }

    @Override
    public Response getAllHorarios(){
        List<HorarioDto> horarios = horarioRepository.findAll().stream()
                .map(entityDtoMapper::mapHorarioToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de horarios obtenida")
                .horarioList(horarios)
                .build();
    }

    @Override
    public Response updateHorario(Long id, HorarioDto horarioDto){
        Horario horario=horarioRepository.findById(id)
                .orElseThrow(()->new NotFountException("Horario no encontrada con ID: "+id));

        if (horarioDto.getHoraInicio() != null && horarioDto.getHoraFin() != null &&
                !horarioDto.getHoraInicio().isBefore(horarioDto.getHoraFin()))
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin.");

        Dia dia = horarioDto.getDia() != null ? Dia.valueOf(horarioDto.getDia()) : horario.getDays();
        LocalTime horaInicio = horarioDto.getHoraInicio() != null ? horarioDto.getHoraInicio() : horario.getHoraInicio();
        LocalTime horaFin = horarioDto.getHoraFin() != null ? horarioDto.getHoraFin() : horario.getHoraFin();

        boolean horarioDuplicado = horarioRepository.existsByDaysAndHoraInicioAndHoraFinAndIdNot(
                dia, horaInicio, horaFin, id
        );

        if (horarioDuplicado) {
            throw new IllegalArgumentException("Ya existe un horario con el mismo día y horas especificadas.");
        }

        if(horarioDto.getDia() !=null) horario.setDays(Dia.valueOf(horarioDto.getDia()));

        if(horarioDto.getHoraInicio() !=null) horario.setHoraInicio(horarioDto.getHoraInicio());

        if(horarioDto.getHoraFin() !=null) horario.setHoraFin(horarioDto.getHoraFin());

        Horario horarioActualizo = horarioRepository.save(horario);
        HorarioDto responseDto = entityDtoMapper.mapHorarioToDtoBasic(horarioActualizo);

        return Response.builder()
                .status(200)
                .message("Horario actualizado exitosamente")
                .horario(responseDto)
                .build();
    }

    @Override
    public Response deleteHorario(Long id){
        Horario horario=horarioRepository.findById(id)
                .orElseThrow(()->new NotFountException("Horario no encontrada con ID: "+id));

        horarioRepository.delete(horario);

        return Response.builder()
                .status(200)
                .message("Horario eliminado exitosamente")
                .build();
    }
}
