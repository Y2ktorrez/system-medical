package com.y2k.hospital.controller;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoExamenDto;
import com.y2k.hospital.dto.TipoInsumoDto;
import com.y2k.hospital.service.interf.TipoExamenService;
import com.y2k.hospital.service.interf.TipoInsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_insumo")
@RequiredArgsConstructor
public class TipoInsumoController {
    private final TipoInsumoService tipoInsumoService;

    @PostMapping("/create")
    public ResponseEntity<Response> createTipoInsumo(@RequestBody TipoInsumoDto tipoInsumoDto){
        Response response=tipoInsumoService.createTipoInsumo(tipoInsumoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getTipoInsumoById(@PathVariable Long id){
        Response response = tipoInsumoService.getTipoInsumoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllTipoInsumo(){
        Response response = tipoInsumoService.getAllTipoInsumo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateTipoInsumo(@PathVariable Long id, @RequestBody TipoInsumoDto tipoInsumoDto){
        Response response = tipoInsumoService.updateTipoInsumo(id,tipoInsumoDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteTipoInsumo(@PathVariable Long id){
        Response response = tipoInsumoService.deleteTipoInsumo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
