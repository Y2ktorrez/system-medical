package com.y2k.hospital.controller;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoAnalisisDto;
import com.y2k.hospital.service.interf.TipoAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_analisis")
@RequiredArgsConstructor
public class TipoAnalisisController {
    private final TipoAnalisisService tipoAnalisisService;

    @PostMapping("/create")
    public ResponseEntity<Response> createTipoAnalisis(@RequestBody TipoAnalisisDto tipoAnalisisDto){
        Response response=tipoAnalisisService.createTipoAnalisis(tipoAnalisisDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getTipoAnalisisById(@PathVariable Long id){
        Response response = tipoAnalisisService.getTipoAnalisisById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllTipoAnalisis(){
        Response response = tipoAnalisisService.getAllTipoAnalisis();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateTipoAnalisis(@PathVariable Long id, @RequestBody TipoAnalisisDto tipoAnalisisDto){
        Response response = tipoAnalisisService.updateTipoAnalisis(id,tipoAnalisisDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteTipoAnalisis(@PathVariable Long id){
        Response response = tipoAnalisisService.deleteTipoAnalisis(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
