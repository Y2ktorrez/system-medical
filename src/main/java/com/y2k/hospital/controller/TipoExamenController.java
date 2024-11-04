package com.y2k.hospital.controller;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoAnalisisDto;
import com.y2k.hospital.dto.TipoExamenDto;
import com.y2k.hospital.service.interf.TipoAnalisisService;
import com.y2k.hospital.service.interf.TipoExamenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_examen")
@RequiredArgsConstructor
public class TipoExamenController {
    private final TipoExamenService tipoExamenService;

    @PostMapping("/create")
    public ResponseEntity<Response> createTipoExamen(@RequestBody TipoExamenDto tipoExamenDto){
        Response response=tipoExamenService.createTipoExamen(tipoExamenDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getTipoExamenById(@PathVariable Long id){
        Response response = tipoExamenService.getTipoExamenById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllTipoExamen(){
        Response response = tipoExamenService.getAllTipoExamen();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateTipoExamen(@PathVariable Long id, @RequestBody TipoExamenDto tipoExamenDto){
        Response response = tipoExamenService.updateTipoExamen(id,tipoExamenDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteTipoExamen(@PathVariable Long id){
        Response response = tipoExamenService.deleteTipoExamen(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
