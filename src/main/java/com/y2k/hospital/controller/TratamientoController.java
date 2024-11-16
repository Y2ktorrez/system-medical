package com.y2k.hospital.controller;

import com.y2k.hospital.dto.ConsultaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TratamientoDto;
import com.y2k.hospital.service.interf.TratamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tratamiento")
@RequiredArgsConstructor
public class TratamientoController {
    private final TratamientoService tratamientoService;

    @PostMapping("/")
    public ResponseEntity<Response> createTratamiento(@RequestBody TratamientoDto tratamientoDto){
        Response response=tratamientoService.createTratamiento(tratamientoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getTratamientoById(@PathVariable Long id){
        Response response=tratamientoService.getTratamientoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAllTratamientos(){
        Response response=tratamientoService.getAllTratamientos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
