package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoExamenDto;
import com.y2k.hospital.entity.TipoExamen;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.TipoExamenRepository;
import com.y2k.hospital.service.interf.TipoExamenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoExamenImpl implements TipoExamenService {
    private final EntityDtoMapper entityDtoMapper;
    private final TipoExamenRepository tipoExamenRepository;

    @Override
    public Response createTipoExamen(TipoExamenDto tipoExamenDto){
        TipoExamen tipoExamen=TipoExamen.builder()
                .costo(tipoExamenDto.getCosto())
                .descripcion(tipoExamenDto.getDescripcion())
                .nombre(tipoExamenDto.getNombre())
                .build();

        TipoExamen tipoExamenGuardado=tipoExamenRepository.save(tipoExamen);

        TipoExamenDto response=entityDtoMapper.mapTipoExamenToDtoBasic(tipoExamenGuardado);

        return Response.builder()
                .status(200)
                .message("Tipo examen registrado exitosamente")
                .tipoExamen(response)
                .build();
    }

    @Override
    public Response getTipoExamenById(Long id){
        TipoExamen tipoExamen=tipoExamenRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo examen no encontrado con ID: "+id));

        TipoExamenDto tipoExamenDto=entityDtoMapper.mapTipoExamenToDtoBasic(tipoExamen);

        return Response.builder()
                .status(200)
                .message("Tipo examen Encontrado")
                .tipoExamen(tipoExamenDto)
                .build();
    }

    @Override
    public Response getAllTipoExamen(){
        List<TipoExamenDto> tipoExamenes=tipoExamenRepository.findAll().stream()
                .map(entityDtoMapper::mapTipoExamenToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de tipos de examenes obtenida")
                .tipoExamenList(tipoExamenes)
                .build();
    }

    @Override
    public Response updateTipoExamen(Long id, TipoExamenDto tipoExamenDto){
        TipoExamen tipoExamen=tipoExamenRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo examen no encontrado con ID: "+id));

        if(tipoExamenDto.getCosto() !=null) tipoExamen.setCosto(tipoExamenDto.getCosto());
        if(tipoExamenDto.getDescripcion() !=null) tipoExamen.setDescripcion(tipoExamenDto.getDescripcion());
        if(tipoExamenDto.getNombre() !=null) tipoExamen.setNombre(tipoExamenDto.getNombre());

        TipoExamen tipoExamenActualizado = tipoExamenRepository.save(tipoExamen);
        TipoExamenDto response= entityDtoMapper.mapTipoExamenToDtoBasic(tipoExamenActualizado);

        return Response.builder()
                .status(200)
                .message("Tipo analisis actualizado exitosamente")
                .tipoExamen(response)
                .build();
    }

    @Override
    public Response deleteTipoExamen(Long id){
        TipoExamen tipoExamen=tipoExamenRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo examen no encontrado con ID: "+id));

        tipoExamenRepository.delete(tipoExamen);

        return Response.builder()
                .status(200)
                .message("Tipo examen eliminado exitosamente")
                .build();
    }
}
