package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class FichaDto {
    private Long id;
    private LocalDate fechaEmision;
    private LocalDate fechaAtencion;
    private LocalTime horaAtencion;
    private Long ci_paciente;
    private Long ci_medico;
    private Long id_especialidad;
    private String nombrePaciente;
    private String nombreMedico;
    private String nombreEspecialidad;
}
