package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoInsumoDto;
import com.y2k.hospital.entity.TipoInsumo;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.TipoInsumoRepository;
import com.y2k.hospital.service.interf.TipoInsumoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoInsumoImpl implements TipoInsumoService {
    private final EntityDtoMapper entityDtoMapper;
    private final TipoInsumoRepository tipoInsumoRepository;

    @Override
    public Response createTipoInsumo(TipoInsumoDto tipoInsumoDto){
        TipoInsumo tipoInsumo=TipoInsumo.builder()
                .descripcion(tipoInsumoDto.getDescripcion())
                .nombre(tipoInsumoDto.getNombre())
                .build();

        TipoInsumo tipoInsumonGuardado=tipoInsumoRepository.save(tipoInsumo);

        TipoInsumoDto response=entityDtoMapper.mapTipoInsumoToDtoBasic(tipoInsumonGuardado);

        return Response.builder()
                .status(200)
                .message("Tipo insumo registrado exitosamente")
                .tipoInsumo(response)
                .build();
    }

    @Override
    public Response getTipoInsumoById(Long id){
        TipoInsumo tipoInsumo=tipoInsumoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo insumo no encontrado con ID: "+id));

        TipoInsumoDto tipoInsumoDto=entityDtoMapper.mapTipoInsumoToDtoBasic(tipoInsumo);

        return Response.builder()
                .status(200)
                .message("Tipo insumo Encontrado")
                .tipoInsumo(tipoInsumoDto)
                .build();
    }

    @Override
    public Response getAllTipoInsumo(){
        List<TipoInsumoDto> tipoInsumos=tipoInsumoRepository.findAll().stream()
                .map(entityDtoMapper::mapTipoInsumoToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de tipos de insumos obtenida")
                .tipoInsumoList(tipoInsumos)
                .build();
    }

    @Override
    public Response updateTipoInsumo(Long id, TipoInsumoDto tipoInsumoDto){
        TipoInsumo tipoInsumo=tipoInsumoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo insumo no encontrado con ID: "+id));

        if(tipoInsumoDto.getDescripcion() !=null) tipoInsumo.setDescripcion(tipoInsumoDto.getDescripcion());
        if(tipoInsumoDto.getNombre() !=null) tipoInsumo.setNombre(tipoInsumoDto.getNombre());

        TipoInsumo tipoInsumoActualizado = tipoInsumoRepository.save(tipoInsumo);
        TipoInsumoDto response= entityDtoMapper.mapTipoInsumoToDtoBasic(tipoInsumoActualizado);

        return Response.builder()
                .status(200)
                .message("Tipo analisis actualizado exitosamente")
                .tipoInsumo(response)
                .build();
    }

    @Override
    public Response deleteTipoInsumo(Long id){
        TipoInsumo tipoInsumo=tipoInsumoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo insumo no encontrado con ID: "+id));

        tipoInsumoRepository.delete(tipoInsumo);

        return Response.builder()
                .status(200)
                .message("Tipo insumo eliminado exitosamente")
                .build();
    }
}
