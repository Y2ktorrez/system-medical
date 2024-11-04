package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.PreconsultaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Enfermero;
import com.y2k.hospital.entity.Ficha;
import com.y2k.hospital.entity.Preconsulta;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.EnfermeroRepository;
import com.y2k.hospital.repository.FichaRepository;
import com.y2k.hospital.repository.PreconsultaRepository;
import com.y2k.hospital.service.interf.PreconsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreconsultaImpl implements PreconsultaService {
    private final PreconsultaRepository preconsultaRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final EnfermeroRepository enfermeroRepository;
    private final FichaRepository fichaRepository;

    @Override
    public Response createPreconsulta(PreconsultaDto preconsultaDto){
        Enfermero enfermero=enfermeroRepository.findById(preconsultaDto.getCi_enferemero())
                .orElseThrow(()-> new NotFountException("Medico no encontrado con CI: " + preconsultaDto.getCi_enferemero()));

        Ficha ficha=fichaRepository.findById(preconsultaDto.getId_Ficha())
                .orElseThrow(()-> new NotFountException("Medico no encontrado con CI: " + preconsultaDto.getCi_enferemero()));

        Preconsulta preconsulta=Preconsulta.builder()
                .altura(preconsultaDto.getAltura())
                .edad(preconsultaDto.getEdad())
                .peso(preconsultaDto.getPeso())
                .sexo(preconsultaDto.getSexo())
                .estado(preconsultaDto.getEstado())
                .enfermero(enfermero)
                .presion(preconsultaDto.getPresion())
                .ficha(ficha)
                .build();

        Preconsulta preconsultaGuardada=preconsultaRepository.save(preconsulta);

        PreconsultaDto response = entityDtoMapper.mapPreconsultaToDtoBasic(preconsultaGuardada);

        return Response.builder()
                .status(200)
                .message("Preconsulta registrada exitosamente")
                .preconsulta(response)
                .build();
    }

    @Override
    public Response getPreconsultaById(Long id){
        Preconsulta preconsulta=preconsultaRepository.findById(id)
                .orElseThrow(()-> new NotFountException("Preconsulta no encontrada con id: " + id));

        PreconsultaDto preconsultaDto=entityDtoMapper.mapPreconsultaToDtoBasic(preconsulta);

        return Response.builder()
                .status(200)
                .message("Preconsulta encontrada")
                .preconsulta(preconsultaDto)
                .build();
    }

    @Override
    public Response getAllPreconsultas(){
        List<PreconsultaDto> preconsultas= preconsultaRepository.findAll().stream()
                .map(entityDtoMapper::mapPreconsultaToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de preconsultas obtenida")
                .preconsultaList(preconsultas)
                .build();
    }

    @Override
    public Response updatePreconsulta(Long id, PreconsultaDto preconsultaDto){
        Preconsulta preconsulta=preconsultaRepository.findById(id)
                .orElseThrow(()-> new NotFountException("Preconsulta no encontrada con id: " + id));
        if (preconsultaDto.getEstado() != null) preconsulta.setEstado(preconsultaDto.getEstado());
        if (preconsultaDto.getPeso() != null) preconsulta.setPeso(preconsultaDto.getPeso());
        if (preconsultaDto.getAltura() != null) preconsulta.setAltura(preconsultaDto.getAltura());
        if (preconsultaDto.getEdad() != null) preconsulta.setEdad(preconsultaDto.getEdad());
        if (preconsultaDto.getSexo() != null) preconsulta.setSexo(preconsultaDto.getSexo());
        if (preconsultaDto.getPresion() != null) preconsulta.setPresion(preconsultaDto.getPresion());

        if (preconsultaDto.getCi_enferemero() != null) {
            Enfermero enfermero = enfermeroRepository.findById(preconsultaDto.getCi_enferemero())
                    .orElseThrow(() -> new NotFountException("Enfermero no encontrado con CI: " + preconsultaDto.getCi_enferemero()));
            preconsulta.setEnfermero(enfermero);
        }

        if (preconsultaDto.getId_Ficha() != null) {
            Ficha ficha = fichaRepository.findById(preconsultaDto.getId_Ficha())
                    .orElseThrow(() -> new NotFountException("Ficha no encontrada con ID: " + preconsultaDto.getId_Ficha()));
            preconsulta.setFicha(ficha);
        }

        // Guardar la preconsulta actualizada
        Preconsulta preconsultaActualizada = preconsultaRepository.save(preconsulta);

        // Mapear la preconsulta actualizada a un DTO de respuesta
        PreconsultaDto responseDto = entityDtoMapper.mapPreconsultaToDtoBasic(preconsultaActualizada);

        return Response.builder()
                .status(200)
                .message("Preconsulta actualizada exitosamente")
                .preconsulta(responseDto)
                .build();
    }

    @Override
    public Response deletePreconsulta(Long id){
        Preconsulta preconsulta=preconsultaRepository.findById(id)
                .orElseThrow(()-> new NotFountException("Preconsulta no encontrada con id: " + id));

        preconsultaRepository.delete(preconsulta);

        return Response.builder()
                .status(200)
                .message("Preconsulta eliminada exitosamente")
                .build();
    }
}
