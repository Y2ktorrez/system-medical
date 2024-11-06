package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.ConsultaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.*;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.*;
import com.y2k.hospital.service.interf.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaImpl implements ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final PreconsultaRepository preconsultaRepository;
    private final TipoExamenRepository tipoExamenRepository;
    private final TipoAnalisisRepository tipoAnalisisRepository;

    @Override
    public Response createConsulta(ConsultaDto consultaDto){
        Preconsulta preconsulta=preconsultaRepository.findById(consultaDto.getId_preconsulta())
                .orElseThrow(()-> new NotFountException("Medico no encontrado con CI: " + consultaDto.getId_preconsulta()));

        TipoAnalisis tipoAnalisis=tipoAnalisisRepository.findById(consultaDto.getId_analisis())
                .orElseThrow(()-> new NotFountException("tipo analisis no encontrado con ID: " + consultaDto.getId_analisis()));

        TipoExamen tipoExamen=tipoExamenRepository.findById(consultaDto.getId_examen())
                .orElseThrow(()-> new NotFountException("tipo examen no encontrado con ID: " + consultaDto.getId_examen()));

        Consulta consulta=Consulta.builder()
                .fecha(consultaDto.getFecha())
                .diagnostico(consultaDto.getDiagnostico())
                .preconsulta(preconsulta)
                .build();

        Consulta consultaGuardada=consultaRepository.save(consulta);

        Analisis analisis=Analisis.builder()
                .resultado(consultaDto.getResultadoAnalisis())
                .tipoAnalisis(tipoAnalisis)
                .consulta(consulta)
                .fecha(consultaDto.getFechaAnalisis())
                .build();

        Examen examen=Examen.builder()
                .resultado(consultaDto.getResultadoAnalisis())
                .tipoExamen(tipoExamen)
                .consulta(consulta)
                .fecha(consultaDto.getFechaAnalisis())
                .build();

        ConsultaDto response=entityDtoMapper.mapConsultaToDtoBasic(consultaGuardada);

        return Response.builder()
                .status(200)
                .message("Consulta registrada exitosamente")
                .consulta(response)
                .build();
    }

    @Override
    public Response getConsultaById(Long id){
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(()-> new NotFountException("Consulta no encontrada con id: " + id));

        ConsultaDto consultaDto=entityDtoMapper.mapConsultaToDtoBasic(consulta);

        return Response.builder()
                .status(200)
                .message("Consulta encontrada")
                .consulta(consultaDto)
                .build();
    }

    @Override
    public Response getAllConsultas(){
        List<ConsultaDto> consultas = consultaRepository.findAll().stream()
                .map(entityDtoMapper::mapConsultaToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de consultas obtenida")
                .consultaList(consultas)
                .build();
    }

    @Override
    public Response updateConsulta(Long id, ConsultaDto consultaDto){
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(()-> new NotFountException("Consulta no encontrada con id: " + id));

        if(consultaDto.getDiagnostico() != null) consulta.setDiagnostico(consultaDto.getDiagnostico());

        if(consultaDto.getFecha() != null) consulta.setFecha(consultaDto.getFecha());

        if(consultaDto.getDiagnostico() != null) consulta.setDiagnostico(consultaDto.getDiagnostico());

        Consulta consultaActualizada = consultaRepository.save(consulta);

        ConsultaDto response= entityDtoMapper.mapConsultaToDtoBasic(consultaActualizada);

        return Response.builder()
                .status(200)
                .message("Consulta actualizada exitosamente")
                .consulta(response)
                .build();
    }

    @Override
    public Response deleteConsulta(Long id){
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(()-> new NotFountException("Consulta no encontrada con id: " + id));

        consultaRepository.delete(consulta);

        return Response.builder()
                .status(200)
                .message("Consulta eliminada exitosamente")
                .build();
    }
}
