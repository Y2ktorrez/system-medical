package com.y2k.hospital.controller;

import com.y2k.hospital.dto.HorarioDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;

    @PostMapping("/create")
    public ResponseEntity<Response> createHorario(@RequestBody HorarioDto horarioDto){
        Response response=horarioService.createHorario(horarioDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getHorarioById(@PathVariable Long id){
        Response response = horarioService.getHorarioById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllHorarios(){
        Response response = horarioService.getAllHorarios();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateHoraio(@PathVariable Long id, @RequestBody HorarioDto horarioDto){
        Response response = horarioService.updateHorario(id,horarioDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteHorario(@PathVariable Long id){
        Response response = horarioService.deleteHorario(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
