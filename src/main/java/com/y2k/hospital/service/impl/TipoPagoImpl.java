package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoPagoDto;
import com.y2k.hospital.entity.TipoPago;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.TipoPagoRepository;
import com.y2k.hospital.service.interf.TipoPagoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoPagoImpl implements TipoPagoService {
    private final EntityDtoMapper entityDtoMapper;
    private final TipoPagoRepository tipoPagoRepository;

    @Override
    public Response createTipoPago(TipoPagoDto tipoPagoDto){
        TipoPago tipoPago=TipoPago.builder()
                .descripcion(tipoPagoDto.getDescripcion())
                .nombre(tipoPagoDto.getNombre())
                .build();

        TipoPago tipoPagoGuardado=tipoPagoRepository.save(tipoPago);

        TipoPagoDto response=entityDtoMapper.mapTipoPagoToDtoBasic(tipoPagoGuardado);

        return Response.builder()
                .status(200)
                .message("Tipo pago registrado exitosamente")
                .tipoPago(response)
                .build();
    }

    @Override
    public Response getTipoPagoById(Long id){
        TipoPago tipoPago=tipoPagoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo pago no encontrado con ID: "+id));

        TipoPagoDto tipoPagoDto=entityDtoMapper.mapTipoPagoToDtoBasic(tipoPago);

        return Response.builder()
                .status(200)
                .message("Tipo insumo Encontrado")
                .tipoPago(tipoPagoDto)
                .build();
    }

    @Override
    public Response getAllTipoPago(){
        List<TipoPagoDto> tipoPagos=tipoPagoRepository.findAll().stream()
                .map(entityDtoMapper::mapTipoPagoToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de tipos de pagos obtenida")
                .tipoPagoList(tipoPagos)
                .build();
    }

    @Override
    public Response updateTipoPago(Long id, TipoPagoDto tipoPagoDto){
        TipoPago tipoPago=tipoPagoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo pago no encontrado con ID: "+id));

        if(tipoPagoDto.getDescripcion() !=null) tipoPago.setDescripcion(tipoPagoDto.getDescripcion());
        if(tipoPagoDto.getNombre() !=null) tipoPago.setNombre(tipoPagoDto.getNombre());

        TipoPago tipoPagoActualizado = tipoPagoRepository.save(tipoPago);
        TipoPagoDto response= entityDtoMapper.mapTipoPagoToDtoBasic(tipoPagoActualizado);

        return Response.builder()
                .status(200)
                .message("Tipo pago actualizado exitosamente")
                .tipoPago(response)
                .build();
    }

    @Override
    public Response deleteTipoPago(Long id){
        TipoPago tipoPago=tipoPagoRepository.findById(id)
                .orElseThrow(()->new NotFountException("Tipo pago no encontrado con ID: "+id));

        tipoPagoRepository.delete(tipoPago);

        return Response.builder()
                .status(200)
                .message("Tipo pago eliminado exitosamente")
                .build();
    }
}
