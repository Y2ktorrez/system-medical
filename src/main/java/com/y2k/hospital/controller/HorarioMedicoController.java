package com.y2k.hospital.controller;

import com.y2k.hospital.dto.HorarioMedicoDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.HorarioMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horario_medico")
@RequiredArgsConstructor
public class HorarioMedicoController {
    private final HorarioMedicoService horarioMedicoService;

    @PostMapping("/create")
    public ResponseEntity<Response> createHorarioMedico(@RequestBody HorarioMedicoDto horarioMedicoDto){
        Response response=horarioMedicoService.createHorarioMedico(horarioMedicoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}/{nombreEspecialidad}")
    public ResponseEntity<Response> getHorarioById(@PathVariable Long id,@PathVariable String nombreEspecialidad){
        Response response = horarioMedicoService.getHorarioMedicoById(id,nombreEspecialidad);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllHorarioMedico(){
        Response response = horarioMedicoService.getAllHorarioMedico();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}/{nombreEspecialidad}")
    public ResponseEntity<Response> deleteHorarioMedico(@PathVariable Long id,@PathVariable String nombreEspecialidad){
        Response response = horarioMedicoService.deleteHorarioMedico(id,nombreEspecialidad);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
