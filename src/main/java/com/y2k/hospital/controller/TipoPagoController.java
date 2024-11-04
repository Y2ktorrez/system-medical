package com.y2k.hospital.controller;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoInsumoDto;
import com.y2k.hospital.dto.TipoPagoDto;
import com.y2k.hospital.service.interf.TipoInsumoService;
import com.y2k.hospital.service.interf.TipoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_pago")
@RequiredArgsConstructor
public class TipoPagoController {
    private final TipoPagoService tipoPagoService;

    @PostMapping("/create")
    public ResponseEntity<Response> createTipoPago(@RequestBody TipoPagoDto tipoPagoDto){
        Response response=tipoPagoService.createTipoPago(tipoPagoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getTipoPagoById(@PathVariable Long id){
        Response response = tipoPagoService.getTipoPagoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllTipoPago(){
        Response response = tipoPagoService.getAllTipoPago();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateTipoPago(@PathVariable Long id, @RequestBody TipoPagoDto tipoPagoDto){
        Response response = tipoPagoService.updateTipoPago(id,tipoPagoDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteTipoPago(@PathVariable Long id){
        Response response = tipoPagoService.deleteTipoPago(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
