package com.y2k.hospital.controller;

import com.y2k.hospital.dto.PreconsultaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.PreconsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preconsulta")
@RequiredArgsConstructor
public class PreconsultaController {
    private final PreconsultaService preconsultaService;

    @PostMapping("/create")
    public ResponseEntity<Response> createPreconsulta(@RequestBody PreconsultaDto preconsultaDto){
        Response response=preconsultaService.createPreconsulta(preconsultaDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Response> getPreconsultaById(@PathVariable Long id){
        Response response=preconsultaService.getPreconsultaById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllPreconsultas(){
        Response response=preconsultaService.getAllPreconsultas();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updatePreconsulta(@PathVariable Long id, @RequestBody PreconsultaDto preconsultaDto){
        Response response=preconsultaService.updatePreconsulta(id,preconsultaDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deletePreconsulta(@PathVariable Long id){
        Response response=preconsultaService.deletePreconsulta(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
