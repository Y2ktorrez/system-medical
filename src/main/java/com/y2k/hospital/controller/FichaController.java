package com.y2k.hospital.controller;

import com.y2k.hospital.dto.FichaDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.FichaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ficha")
@RequiredArgsConstructor
public class FichaController {
    private final FichaService fichaService;

    @PostMapping("/create")
    public ResponseEntity<Response> createFicha(@RequestBody FichaDto fichaDto){
        Response response=fichaService.createFicha(fichaDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Response> getFichaById(@PathVariable Long id){
        Response response = fichaService.getFichaById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllFichas(){
        Response response = fichaService.getAllFichas();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateFicha(@PathVariable Long id, @RequestBody FichaDto fichaDto){
        Response response = fichaService.updateFicha(id,fichaDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteFicha(@PathVariable Long id){
        Response response = fichaService.deleteFicha(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
