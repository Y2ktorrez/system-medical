package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.InsumoMedicoDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.InsumoMedico;
import com.y2k.hospital.entity.TipoInsumo;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.InsumoMedicoRepository;
import com.y2k.hospital.repository.TipoInsumoRepository;
import com.y2k.hospital.service.interf.InsumoMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsumoMedicoImpl implements InsumoMedicoService {
    private final EntityDtoMapper entityDtoMapper;
    private final InsumoMedicoRepository insumoMedicoRepository;
    private final TipoInsumoRepository tipoInsumoRepository;

    @Override
    public Response createInsumoMedico(InsumoMedicoDto insumoMedicoDto){
        TipoInsumo tipoInsumo = tipoInsumoRepository.findById(insumoMedicoDto.getId_tipoInsumo())
                .orElseThrow(()->new NotFountException("Tipo insumo no encontrado con id: "+insumoMedicoDto.getId_tipoInsumo()));

        InsumoMedico insumoMedico=InsumoMedico.builder()
                .costo(insumoMedicoDto.getCosto())
                .descripcion(insumoMedicoDto.getDescripcion())
                .nombre(insumoMedicoDto.getNombre())
                .tipoInsumo(tipoInsumo)
                .build();

        InsumoMedico insumoMedicoGuardado= insumoMedicoRepository.save(insumoMedico);

        InsumoMedicoDto response= entityDtoMapper.mapInsumoMedicoToDtoBasic(insumoMedicoGuardado);

        return Response.builder()
                .status(200)
                .message("Insumo medico registrado exitosamente")
                .insumoMedico(response)
                .build();
    }

    @Override
    public Response getInsumoMedicoById(Long id){
        InsumoMedico insumoMedico=insumoMedicoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Inusmo medico no encontrado con id: " + id));

        InsumoMedicoDto insumoMedicoDto=entityDtoMapper.mapInsumoMedicoToDtoBasic(insumoMedico);

        return Response.builder()
                .status(200)
                .message("Insumo medico encotrado")
                .insumoMedico(insumoMedicoDto)
                .build();
    }

    @Override
    public Response getAllInsumoMedicos(){
        List<InsumoMedicoDto> insumoMedicos = insumoMedicoRepository.findAll().stream()
                .map(entityDtoMapper::mapInsumoMedicoToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de insumos medicos obtenida")
                .insumoMedicoList(insumoMedicos)
                .build();
    }

    @Override
    public Response updateInsumoMedico(Long id, InsumoMedicoDto insumoMedicoDto){
        InsumoMedico insumoMedico=insumoMedicoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Inusmo medico no encontrado con id: " + id));

        if(insumoMedicoDto.getId_tipoInsumo() !=null){
            TipoInsumo tipoInsumo=tipoInsumoRepository.findById(id)
                    .orElseThrow(()->new NotFountException("Tipo de inusmo no encontrado con id: " + insumoMedicoDto.getId_tipoInsumo()));

            insumoMedico.setTipoInsumo(tipoInsumo);
        }

        if (insumoMedicoDto.getNombre() !=null) insumoMedico.setNombre(insumoMedicoDto.getNombre());
        if (insumoMedicoDto.getCosto() !=null) insumoMedico.setCosto(insumoMedicoDto.getCosto());
        if (insumoMedicoDto.getDescripcion() !=null) insumoMedico.setDescripcion(insumoMedicoDto.getDescripcion());

        InsumoMedico insumoMedicoActualizado = insumoMedicoRepository.save(insumoMedico);

        InsumoMedicoDto response = entityDtoMapper.mapInsumoMedicoToDtoBasic(insumoMedicoActualizado);

        return Response.builder()
                .status(200)
                .message("Insumo medico actualizado exitosamente")
                .insumoMedico(response)
                .build();
    }

    @Override
    public Response deleteInsumoMedico(Long id){
        InsumoMedico insumoMedico=insumoMedicoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Inusmo medico no encontrado con id: " + id));

        insumoMedicoRepository.delete(insumoMedico);

        return Response.builder()
                .status(200)
                .message("Insumo medico eliminado exitosamente")
                .build();
    }
}
