package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.AnalisisDto;
import com.y2k.hospital.dto.ConsultaDto;
import com.y2k.hospital.dto.ExamenDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.*;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.*;
import com.y2k.hospital.service.interf.ConsultaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaImpl implements ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final PreconsultaRepository preconsultaRepository;
    private final TipoExamenRepository tipoExamenRepository;
    private final TipoAnalisisRepository tipoAnalisisRepository;
    private final AnalisisRepository analisisRepository;
    private final ExamenRepository examenRepository;

    @Transactional
    @Override
    public Response createConsulta(ConsultaDto consultaDto) {
        Preconsulta preconsulta = preconsultaRepository.findById(consultaDto.getId_preconsulta())
                .orElseThrow(() -> new NotFountException("Medico no encontrado con CI: " + consultaDto.getId_preconsulta()));

        Consulta consulta = Consulta.builder()
                .fecha(consultaDto.getFecha())
                .diagnostico(consultaDto.getDiagnostico())
                .preconsulta(preconsulta)
                .build();

        Consulta consultaGuardada = consultaRepository.save(consulta);

        List<Analisis> listaAnalisis = new ArrayList<>();
        List<Examen> listaExamenes = new ArrayList<>();

        if (consultaDto.getAnalisis() != null) {
            for (AnalisisDto analisisDto : consultaDto.getAnalisis()) {
                TipoAnalisis tipoAnalisis = tipoAnalisisRepository.findById(analisisDto.getId_tipoAnalisis())
                        .orElseThrow(() -> new NotFountException("Tipo de análisis no encontrado con ID: " + analisisDto.getId_tipoAnalisis()));

                Analisis analisis = Analisis.builder()
                        .resultado(analisisDto.getResultado())
                        .tipoAnalisis(tipoAnalisis)
                        .consulta(consultaGuardada)
                        .fecha(analisisDto.getFecha())
                        .build();

                listaAnalisis.add(analisis);
            }
        }

        if (consultaDto.getExamen() != null) {
            for (ExamenDto examenDto : consultaDto.getExamen()) {
                TipoExamen tipoExamen = tipoExamenRepository.findById(examenDto.getId_tipoExamen())
                        .orElseThrow(() -> new NotFountException("Tipo de examen no encontrado con ID: " + examenDto.getId_tipoExamen()));

                Examen examen = Examen.builder()
                        .resultado(examenDto.getResultado())
                        .tipoExamen(tipoExamen)
                        .consulta(consultaGuardada)
                        .fecha(examenDto.getFecha())
                        .build();

                listaExamenes.add(examen);
            }
        }

        if (!listaAnalisis.isEmpty()) {
            analisisRepository.saveAll(listaAnalisis);
        }
        if (!listaExamenes.isEmpty()) {
            examenRepository.saveAll(listaExamenes);
        }

        preconsulta.setPreconsultaTerminada(LocalDate.now());
        preconsultaRepository.save(preconsulta);

        ConsultaDto response = entityDtoMapper.mapConsultaToDtoBasic(consultaGuardada,listaExamenes,listaAnalisis);

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

        List<Examen> examenes = examenRepository.findAllByConsultaId(id);

        List<Analisis> analisis = analisisRepository.findAllByConsultaId(id);

        ConsultaDto consultaDto=entityDtoMapper.mapConsultaToDtoBasic(consulta,examenes,analisis);

        return Response.builder()
                .status(200)
                .message("Consulta encontrada")
                .consulta(consultaDto)
                .build();
    }

    @Override
    public Response getAllConsultas(){
        List<ConsultaDto> consultas = consultaRepository.findAll().stream()
                .map(consulta -> {
                    List<Examen> examenes = examenRepository.findAllByConsultaId(consulta.getId());
                    List<Analisis> analisis = analisisRepository.findAllByConsultaId(consulta.getId());

                    return entityDtoMapper.mapConsultaToDtoBasic(consulta, examenes, analisis);
                })
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

        List<Examen> examenes = examenRepository.findAllByConsultaId(id);

        List<Analisis> analisis = analisisRepository.findAllByConsultaId(id);

        ConsultaDto response= entityDtoMapper.mapConsultaToDtoBasic(consultaActualizada,examenes,analisis);

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
