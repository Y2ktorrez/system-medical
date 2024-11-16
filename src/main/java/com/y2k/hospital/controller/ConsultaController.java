package com.y2k.hospital.controller;

import com.y2k.hospital.dto.ConsultaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping("/create")
    public ResponseEntity<Response> createConsulta(@RequestBody ConsultaDto consultaDto){
        Response response=consultaService.createConsulta(consultaDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Response> getConsultaById(@PathVariable Long id){
        Response response=consultaService.getConsultaById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllConsultas(){
        Response response=consultaService.getAllConsultas();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateConsulta(@PathVariable Long id,@RequestBody ConsultaDto consultaDto){
        Response response=consultaService.updateConsulta(id,consultaDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteConsulta(@PathVariable Long id){
        Response response=consultaService.deleteConsulta(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/historial/{id}")
    public ResponseEntity<Response> historial(@PathVariable Long id){
        Response response=consultaService.historial(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
