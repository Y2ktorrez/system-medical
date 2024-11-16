package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class InsumoTratamientoDto {
    private Long id;
    private Long id_insumoMedico;
    private Long id_tratamiento;

    private Integer costoTotal;
    private Integer cantidad;

    private InsumoMedicoDto insumoMedico;
}
