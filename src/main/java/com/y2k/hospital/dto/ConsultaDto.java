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
public class ConsultaDto {
    private Long id;
    private LocalDate fecha;
    private String diagnostico;
    private Long id_preconsulta;
    private PreconsultaDto preconsultaDto;

    private TipoExamenDto tipoExamen;
    private ExamenDto examen;
    private Long id_examen;
    private String resultadoExamen;
    private LocalDate fechaExamen;

    private TipoAnalisisDto tipoAnalisis;
    private AnalisisDto analisis;
    private Long id_analisis;
    private String resultadoAnalisis;
    private LocalDate fechaAnalisis;
}
