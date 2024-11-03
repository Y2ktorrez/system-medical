package com.y2k.hospital.mapper;

import com.y2k.hospital.dto.*;
import com.y2k.hospital.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoMapper {

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

}

