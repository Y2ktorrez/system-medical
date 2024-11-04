package com.y2k.hospital.controller;

import com.y2k.hospital.dto.InsumoMedicoDto;
import com.y2k.hospital.dto.PreconsultaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.InsumoMedicoService;
import com.y2k.hospital.service.interf.PreconsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insumo_medico")
@RequiredArgsConstructor
public class InusmoMedicoController {
    private final InsumoMedicoService insumoMedicoService;

    @PostMapping("/create")
    public ResponseEntity<Response> createInsumoMedico(@RequestBody InsumoMedicoDto insumoMedicoDto){
        Response response=insumoMedicoService.createInsumoMedico(insumoMedicoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Response> getInsumoMedicoById(@PathVariable Long id){
        Response response=insumoMedicoService.getInsumoMedicoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllInsumoMedicos(){
        Response response=insumoMedicoService.getAllInsumoMedicos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateInsumoMedico(@PathVariable Long id, @RequestBody InsumoMedicoDto insumoMedicoDto){
        Response response=insumoMedicoService.updateInsumoMedico(id,insumoMedicoDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteInsumoMedico(@PathVariable Long id){
        Response response=insumoMedicoService.deleteInsumoMedico(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
