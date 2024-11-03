package com.y2k.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.y2k.hospital.entity.Horario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class HorarioMedicoDto {
    private Long id;
    private Long ci_medico;
    private Long id_horario;
    private Long id_especialidad;
    private String nombreMedico;
    private String nombreEspecialidad;
    private HorarioDto horario;
    private List<Long> id_horarios;
    private List<HorarioDto> horarios;
}
