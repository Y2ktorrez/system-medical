package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoAnalisisDto;
import com.y2k.hospital.entity.TipoAnalisis;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.TipoAnalisisRepository;
import com.y2k.hospital.service.interf.TipoAnalisisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoAnalisisImpl implements TipoAnalisisService {
    private final EntityDtoMapper entityDtoMapper;
    private final TipoAnalisisRepository tipoAnalisisRepository;

    @Override
    public Response createTipoAnalisis(TipoAnalisisDto tipoAnalisisDto){
        TipoAnalisis tipoAnalisis=TipoAnalisis.builder()
                .costo(tipoAnalisisDto.getCosto())
                .descripcion(tipoAnalisisDto.getDescripcion())
                .nombre(tipoAnalisisDto.getNombre())
                .build();

        TipoAnalisis tipoAnalisisGuardado=tipoAnalisisRepository.save(tipoAnalisis);

        TipoAnalisisDto response=entityDtoMapper.mapTipoAnalisisToDtoBasic(tipoAnalisisGuardado);

        return Response.builder()
                .status(200)
                .message("Tipo analisis registrado exitosamente")
                .tipoAnalisis(response)
                .build();
    }

    @Override
    public Response getTipoAnalisisById(Long id){
        TipoAnalisis tipoAnalisis=tipoAnalisisRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo analisis no encontrado con ID: "+id));

        TipoAnalisisDto tipoAnalisisDto=entityDtoMapper.mapTipoAnalisisToDtoBasic(tipoAnalisis);

        return Response.builder()
                .status(200)
                .message("Tipo Analisis Encontrado")
                .tipoAnalisis(tipoAnalisisDto)
                .build();
    }

    @Override
    public Response getAllTipoAnalisis(){
        List<TipoAnalisisDto> tipoAnalisis=tipoAnalisisRepository.findAll().stream()
                .map(entityDtoMapper::mapTipoAnalisisToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de tipos de analisis obtenida")
                .tipoAnalisisList(tipoAnalisis)
                .build();
    }

    @Override
    public Response updateTipoAnalisis(Long id, TipoAnalisisDto tipoAnalisisDto){
        TipoAnalisis tipoAnalisis=tipoAnalisisRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo analisis no encontrado con ID: "+id));

        if(tipoAnalisisDto.getCosto() !=null) tipoAnalisis.setCosto(tipoAnalisisDto.getCosto());
        if(tipoAnalisisDto.getDescripcion() !=null) tipoAnalisis.setDescripcion(tipoAnalisisDto.getDescripcion());
        if(tipoAnalisisDto.getNombre() !=null) tipoAnalisis.setNombre(tipoAnalisisDto.getNombre());

        TipoAnalisis tipoAnalisisActualizado = tipoAnalisisRepository.save(tipoAnalisis);
        TipoAnalisisDto response= entityDtoMapper.mapTipoAnalisisToDtoBasic(tipoAnalisisActualizado);

        return Response.builder()
                .status(200)
                .message("Tipo analisis actualizado exitosamente")
                .tipoAnalisis(response)
                .build();
    }

    @Override
    public Response deleteTipoAnalisis(Long id){
        TipoAnalisis tipoAnalisis=tipoAnalisisRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo analisis no encontrado con ID: "+id));

        tipoAnalisisRepository.delete(tipoAnalisis);

        return Response.builder()
                .status(200)
                .message("Tipo anaisis eliminado exitosamente")
                .build();
    }
}
