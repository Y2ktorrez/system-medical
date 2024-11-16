package com.y2k.hospital.mapper;

import com.y2k.hospital.dto.*;
import com.y2k.hospital.entity.*;
import com.y2k.hospital.repository.HorarioMedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntityDtoMapper {
    private final HorarioMedicoRepository horarioMedicoRepository;

    //EspecialidadDto
    public EspecialidadDto mapEspecialidadToDtoBasic(Especialidad especialidad) {
        EspecialidadDto especialidadDto = new EspecialidadDto();
        especialidadDto.setId(especialidad.getId());
        especialidadDto.setNombre(especialidad.getNombre());
        especialidadDto.setDescripcion(especialidad.getDescripcion());
        return especialidadDto;
    }

    //MedicoDto
    public MedicoDto mapMedicoToDtoBasic(Medico medico) {
        MedicoDto medicoDto = new MedicoDto();
        medicoDto.setCi(medico.getCi());
        medicoDto.setName(medico.getUser().getNombre());
        medicoDto.setEmail(medico.getUser().getEmail());
        medicoDto.setAge(medico.getEdad());
        medicoDto.setBirthDate(medico.getFechaNacimiento());
        List<String> especialidades = medico.getEspecialidades().stream()
                .map(Especialidad::getNombre)
                .collect(Collectors.toList());
        medicoDto.setEspecialidades(especialidades);
        List<EspecialidadDto> especialidadDtoList= medico.getEspecialidades().stream()
                .map(this::mapEspecialidadToDtoBasic)
                .toList();
        medicoDto.setListaEspecialidades(especialidadDtoList);

        return medicoDto;
    }

    //EnfermeroDto
    public EnfermeroDto mapEnfermeroToDtoBasic(Enfermero enfermero){
        EnfermeroDto enfermeroDto = new EnfermeroDto();
        enfermeroDto.setCi(enfermero.getCi());
        enfermeroDto.setName(enfermero.getUser().getNombre());
        enfermeroDto.setEmail(enfermero.getUser().getEmail());
        enfermeroDto.setAge(enfermero.getEdad());
        enfermeroDto.setBirthDate(enfermero.getFechaNacimiento());
        return enfermeroDto;
    }

    //PacienteDto
    public PacienteDto mapPacienteToDtoBasic(Paciente paciente){
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setCi(paciente.getCi());
        pacienteDto.setName(paciente.getUser().getNombre());
        pacienteDto.setEmail(paciente.getUser().getEmail());
        pacienteDto.setBirthDate(paciente.getFechaNacimiento());
        return pacienteDto;
    }

    //FichaDto
    public FichaDto mapFichaToDtoBasic(Ficha ficha){
        FichaDto fichaDto= new FichaDto();
        fichaDto.setId(ficha.getId());
        fichaDto.setFechaEmision(ficha.getFechaEmision());
        fichaDto.setFechaAtencion(ficha.getFechaAtencion());
        fichaDto.setHoraAtencion(ficha.getHoraAtencion());
        fichaDto.setCi_medico(ficha.getMedico().getCi());
        fichaDto.setCi_paciente(ficha.getPaciente().getCi());
        fichaDto.setId_especialidad(ficha.getEspecialidad().getId());
        fichaDto.setNombreEspecialidad(ficha.getEspecialidad().getNombre());
        fichaDto.setNombreMedico(ficha.getMedico().getUser().getNombre());
        fichaDto.setNombrePaciente(ficha.getPaciente().getUser().getNombre());
        fichaDto.setFichaCancelada(ficha.getFichaCancelada());
        fichaDto.setFichaTerminada(ficha.getFichaTerminada());
        return fichaDto;
    }

    //HorarioDto
    public HorarioDto mapHorarioToDtoBasic(Horario horario){
        HorarioDto horarioDto= new HorarioDto();
        horarioDto.setId(horario.getId());
        horarioDto.setDia(horario.getDays().toString());
        horarioDto.setHoraInicio(horario.getHoraInicio());
        horarioDto.setHoraFin(horario.getHoraFin());
        return horarioDto;
    }

    //HorarioMedicoDto
    public HorarioMedicoDto mapHorarioMedicoToDtoBasic(HorarioMedico horarioMedico) {
        HorarioMedicoDto horarioMedicoDto = new HorarioMedicoDto();
        horarioMedicoDto.setCi_medico(horarioMedico.getMedico().getCi());
        horarioMedicoDto.setNombreMedico(horarioMedico.getMedico().getUser().getNombre());
        horarioMedicoDto.setId(horarioMedico.getId());
        horarioMedicoDto.setId_horario(horarioMedico.getHorario().getId());
        horarioMedicoDto.setId_especialidad(horarioMedico.getEspecialidad().getId());
        horarioMedicoDto.setNombreEspecialidad(horarioMedico.getEspecialidad().getNombre());

        // Obtener todos los horarios del médico correspondiente
        List<HorarioMedico> horariosMedicos = horarioMedicoRepository.findByMedico_Ci(horarioMedico.getMedico().getCi());

        // Mapear IDs de horarios y objetos Horario a la respuesta DTO
        List<Long> idHorarios = horariosMedicos.stream()
                .map(hm -> hm.getHorario().getId())
                .collect(Collectors.toList());

        // Convertir objetos Horario a HorarioDto
        List<HorarioDto> horariosDto = horariosMedicos.stream()
                .map(HorarioMedico::getHorario) // Obtener la lista de horarios
                .map(this::mapHorarioToDtoBasic) // Convertir cada horario a HorarioDto
                .collect(Collectors.toList());

        // Asignar a DTO
        horarioMedicoDto.setId_horarios(idHorarios);
        horarioMedicoDto.setHorarios(horariosDto); // Cambia a usar la lista de HorarioDto
        return horarioMedicoDto;
    }

    //PreconsultaDto
    public PreconsultaDto mapPreconsultaToDtoBasic(Preconsulta preconsulta){
        PreconsultaDto preconsultaDto=new PreconsultaDto();
        preconsultaDto.setId(preconsulta.getId());
        preconsultaDto.setEdad(preconsulta.getEdad());
        preconsultaDto.setEstado(preconsulta.getEstado());
        preconsultaDto.setPeso(preconsulta.getPeso());
        preconsultaDto.setAltura(preconsulta.getAltura());
        preconsultaDto.setSexo(preconsulta.getSexo());
        preconsultaDto.setPresion(preconsulta.getPresion());
        preconsultaDto.setEnfermero(mapEnfermeroToDtoBasic(preconsulta.getEnfermero()));
        preconsultaDto.setNombreEnfermero(preconsulta.getEnfermero().getUser().getNombre());
        preconsultaDto.setFicha(mapFichaToDtoBasic(preconsulta.getFicha()));
        preconsultaDto.setCi_enferemero(preconsulta.getEnfermero().getCi());
        preconsultaDto.setId_Ficha(preconsulta.getFicha().getId());
        preconsultaDto.setPreconsultaTerminada(preconsulta.getPreconsultaTerminada());

        return preconsultaDto;
    }

    //ConsultaDto Todo: Hay que modificarlo despues de crear las demas tablas!!!
    public ConsultaDto mapConsultaToDtoBasic(Consulta consulta, List<Examen> examen, List<Analisis> analisis){
        ConsultaDto consultaDto=new ConsultaDto();
        consultaDto.setId(consulta.getId());
        consultaDto.setFecha(consulta.getFecha());
        consultaDto.setDiagnostico(consulta.getDiagnostico());
        consultaDto.setId_preconsulta(consulta.getPreconsulta().getId());
        consultaDto.setPreconsultaDto(mapPreconsultaToDtoBasic(consulta.getPreconsulta()));
        consultaDto.setConsultaTerminada(consulta.getConsultaTerminada());

        consultaDto.setAnalisis(mapListAnalisisToDtoBasic(analisis));

        consultaDto.setExamen(mapListExamenToDtoBasic(examen));

        return consultaDto;
    }

    //TipoAnalisis
    public TipoAnalisisDto mapTipoAnalisisToDtoBasic(TipoAnalisis tipoAnalisis){
        TipoAnalisisDto tipoAnalisisDto=new TipoAnalisisDto();
        tipoAnalisisDto.setCosto(tipoAnalisis.getCosto());
        tipoAnalisisDto.setId(tipoAnalisis.getId());
        tipoAnalisisDto.setNombre(tipoAnalisis.getNombre());
        tipoAnalisisDto.setDescripcion(tipoAnalisis.getDescripcion());

        return tipoAnalisisDto;
    }

    //TipoExamen
    public TipoExamenDto mapTipoExamenToDtoBasic(TipoExamen tipoExamen){
        TipoExamenDto tipoExamenDto=new TipoExamenDto();
        tipoExamenDto.setCosto(tipoExamen.getCosto());
        tipoExamenDto.setId(tipoExamen.getId());
        tipoExamenDto.setNombre(tipoExamen.getNombre());
        tipoExamenDto.setDescripcion(tipoExamen.getDescripcion());

        return tipoExamenDto;
    }

    //TipoInsumo
    public TipoInsumoDto mapTipoInsumoToDtoBasic(TipoInsumo tipoInsumo){
        TipoInsumoDto tipoInsumoDto=new TipoInsumoDto();
        tipoInsumoDto.setId(tipoInsumo.getId());
        tipoInsumoDto.setNombre(tipoInsumo.getNombre());
        tipoInsumoDto.setDescripcion(tipoInsumo.getDescripcion());

        return tipoInsumoDto;
    }

    //TipoPago
    public TipoPagoDto mapTipoPagoToDtoBasic(TipoPago tipoPago){
        TipoPagoDto tipoPagoDto=new TipoPagoDto();
        tipoPagoDto.setId(tipoPago.getId());
        tipoPagoDto.setNombre(tipoPago.getNombre());
        tipoPagoDto.setDescripcion(tipoPago.getDescripcion());

        return tipoPagoDto;
    }

    //InsumoMedico
    public InsumoMedicoDto mapInsumoMedicoToDtoBasic(InsumoMedico insumoMedico){
        InsumoMedicoDto insumoMedicoDto=new InsumoMedicoDto();
        insumoMedicoDto.setId(insumoMedico.getId());
        insumoMedicoDto.setNombre(insumoMedico.getNombre());
        insumoMedicoDto.setDescripcion(insumoMedico.getDescripcion());
        insumoMedicoDto.setCosto(insumoMedico.getCosto());
        insumoMedicoDto.setId_tipoInsumo(insumoMedico.getId());
        insumoMedicoDto.setTipoInsumo(mapTipoInsumoToDtoBasic(insumoMedico.getTipoInsumo()));

        return insumoMedicoDto;
    }

    //Analisis
    public AnalisisDto mapAnalisisToDtoBasic(Analisis analisis){
        AnalisisDto analisisDto= new AnalisisDto();
        analisisDto.setId(analisis.getId());
        analisisDto.setResultado(analisis.getResultado());
        analisisDto.setFecha(analisis.getFecha());
        analisisDto.setAnalisis(mapTipoAnalisisToDtoBasic(analisis.getTipoAnalisis()));

        return analisisDto;
    }

    //Examen
    public ExamenDto mapExamenToDtoBasic(Examen examen){
        ExamenDto examenDto= new ExamenDto();
        examenDto.setId(examen.getId());
        examenDto.setResultado(examen.getResultado());
        examenDto.setFecha(examen.getFecha());
        examenDto.setExamen(mapTipoExamenToDtoBasic(examen.getTipoExamen()));

        return examenDto;
    }

    //ListAnalisis
    public List<AnalisisDto> mapListAnalisisToDtoBasic (List<Analisis> analisis){
        return analisis.stream()
                .map(this::mapAnalisisToDtoBasic)
                .toList();
    }

    //ListExamen
    public List<ExamenDto> mapListExamenToDtoBasic (List<Examen> examen){
        return examen.stream()
                .map(this::mapExamenToDtoBasic)
                .toList();
    }

    //Tratamiento
    public TratamientoDto mapTratamientoToDtoBasic (Tratamiento tratamiento,List<InsumoTratamiento> insumoTratamientos){
        TratamientoDto tratamientoDto=new TratamientoDto();
        tratamientoDto.setId(tratamiento.getId());
        tratamientoDto.setId_consulta(tratamiento.getConsulta().getId());
        tratamientoDto.setDescripcion(tratamiento.getDescripcion());
        tratamientoDto.setFecha(tratamiento.getFecha());
        tratamientoDto.setTratamientoTerminado(tratamiento.getTratamientoTerminado());

        tratamientoDto.setInsumoTratamiento(mapListInsumoTratamientoToDtoBasic(insumoTratamientos));
        return tratamientoDto;
    }

    //InsumoTratamiento
    public InsumoTratamientoDto mapInsumoTratamientoToDoBasic(InsumoTratamiento insumoTratamiento){
        InsumoTratamientoDto insumoTratamientoDto= new InsumoTratamientoDto();
        insumoTratamientoDto.setId(insumoTratamiento.getId());
        insumoTratamientoDto.setId_tratamiento(insumoTratamiento.getTratamiento().getId());
        insumoTratamientoDto.setId_insumoMedico(insumoTratamiento.getInsumoMedico().getId());
        insumoTratamientoDto.setInsumoMedico(mapInsumoMedicoToDtoBasic(insumoTratamiento.getInsumoMedico()));
        insumoTratamientoDto.setCantidad(insumoTratamiento.getCantidad());
        insumoTratamientoDto.setCostoTotal(insumoTratamiento.getCostoTotal());

        return  insumoTratamientoDto;
    }

    // Mapear una lista de InsumoTratamiento a InsumoTratamientoDto
    public List<InsumoTratamientoDto> mapListInsumoTratamientoToDtoBasic(List<InsumoTratamiento> insumoTratamientos) {
        return insumoTratamientos.stream()
                .map(this::mapInsumoTratamientoToDoBasic)
                .toList();
    }

    //Historial
    public HistorailDto mapHistorialToDoDtoBasic(Long id, List<ConsultaDto> consultaDto){
        HistorailDto historial= new HistorailDto();
        historial.setId_paciente(id);
        historial.setConsultas(consultaDto);

        return historial;
    }

    //Pago
    public PagoDto mapPagoToDtoBasic(Pago pago) {
        PagoDto pagoDto = new PagoDto();
        pagoDto.setId(pago.getId());
        pagoDto.setDescription(pago.getDescripcion());
        pagoDto.setCostoTotal(pago.getCostoTotal());
        pagoDto.setTipoPago(pago.getTipoPago().name());
        pagoDto.setIdConsulta(pago.getConsulta().getId());
        pagoDto.setCancelado(pago.isCancelado());
        return pagoDto;
    }

}

