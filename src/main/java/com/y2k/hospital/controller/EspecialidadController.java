package com.y2k.hospital.controller;

import com.y2k.hospital.dto.EspecialidadDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @PostMapping("/create")
    public ResponseEntity<Response> createEspecialidad(@RequestBody EspecialidadDto especialidadDto) {
        Response response = especialidadService.createEspecialidad(especialidadDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getEspecialidadById(@PathVariable Long id) {
        Response response = especialidadService.getEspecialidadById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllEspecialidades() {
        Response response = especialidadService.getAllEspecialidades();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateEspecialidad(@PathVariable Long id,
                                                       @RequestBody EspecialidadDto especialidadDto) {
        Response response = especialidadService.updateEspecialidad(id, especialidadDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteEspecialidad(@PathVariable Long id) {
        Response response = especialidadService.deleteEspecialidad(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
