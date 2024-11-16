package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TratamientoDto {
    private Long id;
    private Long id_consulta;

    private String descripcion;
    private LocalDate fecha;

    private LocalDate tratamientoTerminado;

    private List<InsumoTratamientoDto> insumoTratamiento;

}
