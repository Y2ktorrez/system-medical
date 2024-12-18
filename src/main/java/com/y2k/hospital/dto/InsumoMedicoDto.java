package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.y2k.hospital.entity.TipoInsumo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class InsumoMedicoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer costo;
    private Long id_tipoInsumo;
    private TipoInsumoDto tipoInsumo;
}
