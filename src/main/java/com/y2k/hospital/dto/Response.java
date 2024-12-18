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

    private FichaDto ficha;
    private List<FichaDto> fichaList;

    private HorarioDto horario;
    private List<HorarioDto> horarioList;

    private HorarioMedicoDto horarioMedico;
    private List<HorarioMedicoDto> horarioMedicoList;

    private PreconsultaDto preconsulta;
    private List<PreconsultaDto> preconsultaList;

    private ConsultaDto consulta;
    private List<ConsultaDto> consultaList;

    private TipoAnalisisDto tipoAnalisis;
    private List<TipoAnalisisDto> tipoAnalisisList;

    private TipoExamenDto tipoExamen;
    private List<TipoExamenDto> tipoExamenList;

    private TipoInsumoDto tipoInsumo;
    private List<TipoInsumoDto> tipoInsumoList;

    private TipoPagoDto tipoPago;
    private List<TipoPagoDto> tipoPagoList;

    private InsumoMedicoDto insumoMedico;
    private List<InsumoMedicoDto> insumoMedicoList;

    private TratamientoDto tratamiento;
    private List<TratamientoDto> tratamientoList;

    private HistorailDto historail;
    private List<HistorailDto> historailList;

    private PagoDto pagoDto;
    private List<PagoDto> pagoDtos;
}
