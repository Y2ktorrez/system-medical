package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PreconsultaDto {
    private Long id;
    private String estado;
    private Integer peso;
    private Integer altura;
    private Integer edad;
    private String sexo;
    private String presion;
    private EnfermeroDto enfermero;
    private Long ci_enferemero;
    private FichaDto ficha;
    private Long id_Ficha;
    private String nombreEnfermero;
    private LocalDate preconsultaTerminada;
}
