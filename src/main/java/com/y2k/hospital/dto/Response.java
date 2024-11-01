package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expirationTime;

    //Agregamos los demas DTOS
    private EspecialidadDto especialidad;
    private List<EspecialidadDto> especialidadList;

    private MedicoDto medico;
    private List<MedicoDto> medicoList;

    private EnfermeroDto enfermero;
    private List<EnfermeroDto> enfermeroList;

    private PacienteDto paciente;
    private List<PacienteDto> pacienteList;
}
