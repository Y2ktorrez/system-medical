package com.y2k.hospital.service.impl;

import com.y2k.hospital.dto.HorarioDto;
import com.y2k.hospital.dto.HorarioMedicoDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.Especialidad;
import com.y2k.hospital.entity.Horario;
import com.y2k.hospital.entity.HorarioMedico;
import com.y2k.hospital.entity.Medico;
import com.y2k.hospital.exception.NotFountException;
import com.y2k.hospital.mapper.EntityDtoMapper;
import com.y2k.hospital.repository.EspecialidadRepository;
import com.y2k.hospital.repository.HorarioMedicoRepository;
import com.y2k.hospital.repository.HorarioRepository;
import com.y2k.hospital.repository.MedicoRepository;
import com.y2k.hospital.service.interf.HorarioMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioMedicoImpl implements HorarioMedicoService {
    private final HorarioMedicoRepository horarioMedicoRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final MedicoRepository medicoRepository;
    private final HorarioRepository horarioRepository;
    private final EspecialidadRepository especialidadRepository;

    @Override
    public Response createHorarioMedico(HorarioMedicoDto horarioMedicoDto){
        Medico medico= medicoRepository.findById(horarioMedicoDto.getCi_medico())
                .orElseThrow(() -> new NotFountException("Medico no encontrado con CI: " + horarioMedicoDto.getCi_medico()));

        Especialidad especialidad = especialidadRepository.findById(horarioMedicoDto.getId_especialidad())
                .orElseThrow(() -> new NotFountException("Especialidad no encontrada con ID: " + horarioMedicoDto.getId_especialidad()));

        List<HorarioMedicoDto> horariosGuardados = new ArrayList<>();

        for (Long idHorario : horarioMedicoDto.getId_horarios()) {
            // Busca el horario correspondiente por ID
            Horario horario = horarioRepository.findById(idHorario)
                    .orElseThrow(() -> new NotFountException("Horario no encontrado con ID: " + idHorario));
            //Todo: Tener cuidado ya que si un horario esta mal no aborta los demas, osea los anteriores si se registran

            // Crea una nueva instancia de HorarioMedico
            HorarioMedico horarioMedico = HorarioMedico.builder()
                    .horario(horario)
                    .medico(medico)
                    .especialidad(especialidad) // Asumiendo que has agregado la especialidad en el DTO
                    .build();

            // Guarda el horario médico
            HorarioMedico horarioMedicoGuardado = horarioMedicoRepository.save(horarioMedico);

            // Mapea el objeto guardado a DTO
            HorarioMedicoDto responseDto = entityDtoMapper.mapHorarioMedicoToDtoBasic(horarioMedicoGuardado);
            horariosGuardados.add(responseDto);
        }


        return Response.builder()
                .status(200)
                .message("Horario medico registrado exitosamente")
                .horarioMedico(horarioMedicoDto)
                .build();
    }

    @Override
    public Response getHorarioMedicoById(Long id, String nombreEspecialidad){
        Medico medico= medicoRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Medico no encontrado con CI: " + id));

        List<HorarioMedico> horariosMedicos = horarioMedicoRepository.findByMedico_Ci(medico.getCi()).stream()
                .filter(hm -> hm.getEspecialidad().getNombre().equalsIgnoreCase(nombreEspecialidad))
                .toList();

        if (horariosMedicos.isEmpty()) {
            throw new NotFountException("No se encontraron horarios para el medico con la especialidad: " + nombreEspecialidad);
        }

        HorarioMedicoDto horarioMedicoDto = new HorarioMedicoDto();
        horarioMedicoDto.setCi_medico(medico.getCi());
        horarioMedicoDto.setNombreMedico(medico.getUser().getNombre());

        List<Long> idHorarios = horariosMedicos.stream()
                .map(hm -> hm.getHorario().getId())
                .toList();

        List<HorarioDto> horariosDto = horariosMedicos.stream()
                .map(HorarioMedico::getHorario) // Obtener la lista de horarios
                .map(entityDtoMapper::mapHorarioToDtoBasic) // Convertir cada horario a HorarioDto
                .toList();

        horarioMedicoDto.setId_horarios(idHorarios);
        horarioMedicoDto.setHorarios(horariosDto);

        horarioMedicoDto.setNombreEspecialidad(nombreEspecialidad);

        return Response.builder()
                .status(200)
                .message("Horarios econtrados del medico: "+medico.getUser().getNombre())
                .horarioMedico(horarioMedicoDto)
                .build();
    }

    @Override
    public Response getAllHorarioMedico() {
        List<Medico> medicos = medicoRepository.findAll();

        List<HorarioMedicoDto> horarioMedicoDtos = new ArrayList<>();

        for (Medico medico : medicos) {

            // Iterar sobre cada especialidad del médico
            for (Especialidad especialidad : medico.getEspecialidades()) {

                // Obtener los horarios asociados al médico y la especialidad actual
                List<HorarioMedico> horariosMedicos = horarioMedicoRepository.findByMedico_Ci(medico.getCi()).stream()
                        .filter(hm -> hm.getEspecialidad().getId().equals(especialidad.getId())) // Cambiado para comparación por ID
                        .toList();

                // Solo continuar si hay horarios para esta especialidad
                if (!horariosMedicos.isEmpty()) {
                    // Crear un nuevo HorarioMedicoDto para la especialidad actual
                    HorarioMedicoDto horarioMedicoDto = new HorarioMedicoDto();
                    horarioMedicoDto.setCi_medico(medico.getCi());
                    horarioMedicoDto.setNombreMedico(medico.getUser().getNombre());
                    horarioMedicoDto.setNombreEspecialidad(especialidad.getNombre());
                    horarioMedicoDto.setId_especialidad(especialidad.getId());

                    // Extraer IDs de horarios y objetos Horario
                    List<Long> idHorarios = horariosMedicos.stream()
                            .map(hm -> hm.getHorario().getId())
                            .toList();
                    List<HorarioDto> horariosDto = horariosMedicos.stream()
                            .map(HorarioMedico::getHorario)
                            .map(entityDtoMapper::mapHorarioToDtoBasic)
                            .toList();

                    // Asignar las listas al DTO
                    horarioMedicoDto.setId_horarios(idHorarios);
                    horarioMedicoDto.setHorarios(horariosDto);

                    // Agregar el HorarioMedicoDto a la lista principal
                    horarioMedicoDtos.add(horarioMedicoDto);
                }
            }
        }

        return Response.builder()
                .status(200)
                .message("Listas de horarios de los medicos obtenidos")
                .horarioMedicoList(horarioMedicoDtos)
                .build();
    }

    @Override
    public Response deleteHorarioMedico(Long id, String nombreEspecialidad){
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Médico no encontrado con CI: " + id));

        Especialidad especialidad = especialidadRepository.findByNombre(nombreEspecialidad)
                .orElseThrow(() -> new NotFountException("Especialidad no encontrada: " + nombreEspecialidad));

        List<HorarioMedico> horariosMedicos = horarioMedicoRepository.findByMedico_CiAndEspecialidad(id, especialidad);

        horarioMedicoRepository.deleteAll(horariosMedicos);

        return Response.builder()
                .status(200)
                .message("Horarios de la especialidad '" + nombreEspecialidad + "' eliminados para el médico: " + medico.getUser().getNombre())
                .build();
    }
}
