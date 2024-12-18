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
public class ConsultaDto {
    private Long id;
    private LocalDate fecha;
    private String diagnostico;
    private Long id_preconsulta;
    private PreconsultaDto preconsultaDto;

    private LocalDate consultaTerminada;

    private List<ExamenDto> examen;

    private List<AnalisisDto> analisis;

    private TratamientoDto tratamientos;
}
