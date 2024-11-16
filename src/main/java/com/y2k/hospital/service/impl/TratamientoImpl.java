package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.InsumoTratamientoDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TratamientoDto;
import com.y2k.hospital.entity.Consulta;
import com.y2k.hospital.entity.InsumoMedico;
import com.y2k.hospital.entity.InsumoTratamiento;
import com.y2k.hospital.entity.Tratamiento;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.ConsultaRepository;
import com.y2k.hospital.repository.InsumoMedicoRepository;
import com.y2k.hospital.repository.InsumoTratamientoRepository;
import com.y2k.hospital.repository.TratamientoRepository;
import com.y2k.hospital.service.interf.TratamientoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TratamientoImpl implements TratamientoService {
    private final ConsultaRepository consultaRepository;
    private final TratamientoRepository tratamientoRepository;
    private final InsumoTratamientoRepository insumoTratamientoRepository;
    private final InsumoMedicoRepository insumoMedicoRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Transactional
    @Override
    public Response createTratamiento(TratamientoDto tratamientoDto) {
        // Validar que exista la consulta asociada al tratamiento
        Consulta consulta = consultaRepository.findById(tratamientoDto.getId_consulta())
                .orElseThrow(() -> new NotFountException("Consulta no encontrada con ID: " + tratamientoDto.getId_consulta()));

        // Crear el tratamiento con los datos iniciales
        Tratamiento tratamiento = Tratamiento.builder()
                .descripcion(tratamientoDto.getDescripcion())
                .fecha(tratamientoDto.getFecha())
                .consulta(consulta)
                .build();

        Tratamiento tratamientoGuardado = tratamientoRepository.save(tratamiento);

        // Lista para almacenar los insumoTratamientos creados
        List<InsumoTratamiento> listaInsumoTratamientos = new ArrayList<>();

        // Crear insumos asociados al tratamiento
        if (tratamientoDto.getInsumoTratamiento() != null) {
            for (InsumoTratamientoDto insumoDto : tratamientoDto.getInsumoTratamiento()) {
                // Validar que exista el insumo médico asociado
                InsumoMedico insumoMedico = insumoMedicoRepository.findById(insumoDto.getId_insumoMedico())
                        .orElseThrow(() -> new NotFountException("Insumo médico no encontrado con ID: " + insumoDto.getId_insumoMedico()));

                // Calcular el costo total: costo del insumo * cantidad
                Integer costoTotal = insumoMedico.getCosto() * insumoDto.getCantidad();

                // Crear el insumoTratamiento
                InsumoTratamiento insumoTratamiento = InsumoTratamiento.builder()

                        .tratamiento(tratamientoGuardado)
                        .insumoMedico(insumoMedico)
                        .cantidad(insumoDto.getCantidad())
                        .costoTotal(costoTotal)  // Asignar el costo total calculado
                        .build();

                listaInsumoTratamientos.add(insumoTratamiento);
            }

            // Guardar todos los insumoTratamientos
            insumoTratamientoRepository.saveAll(listaInsumoTratamientos);
        }

        consulta.setConsultaTerminada(LocalDate.now());
        consultaRepository.save(consulta);

        // Mapear los datos guardados para la respuesta
        TratamientoDto responseDto = entityDtoMapper.mapTratamientoToDtoBasic(tratamientoGuardado, listaInsumoTratamientos);

        // Retornar la respuesta
        return Response.builder()
                .status(200)
                .message("Tratamiento creado exitosamente")
                .tratamiento(responseDto)
                .build();
    }


    @Override
    public Response getTratamientoById(Long id){
        Tratamiento tratamiento = tratamientoRepository.findById(id).
        orElseThrow(()-> new NotFountException("Tratamiento no encontrada con id: " + id));

        List<InsumoTratamiento> insumoTratamientos = insumoTratamientoRepository.findByTratamientoId(id);

        // Mapear el tratamiento a un DTO
        TratamientoDto tratamientoDto = entityDtoMapper.mapTratamientoToDtoBasic(tratamiento, insumoTratamientos);

        // Construir la respuesta
        return Response.builder()
                .status(200)
                .message("Tratamiento encontrado exitosamente")
                .tratamiento(tratamientoDto)
                .build();
    }

    @Override
    public Response getAllTratamientos(){
        List<TratamientoDto> tratamientos = tratamientoRepository.findAll().stream()
                .map(tratamiento -> {
                    List<InsumoTratamiento> insumoTratamientos= insumoTratamientoRepository.findByTratamientoId(tratamiento.getId());
                    return entityDtoMapper.mapTratamientoToDtoBasic(tratamiento,insumoTratamientos);
                })
                .toList();

        return Response.builder()
                .status(200)
                .message("Lista de tratamientos obtenida")
                .tratamientoList(tratamientos)
                .build();
    }
}
