package com.y2k.hospital.controller;

import com.y2k.hospital.dto.EnfermeroDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.EnfermeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enfermero")
@RequiredArgsConstructor
public class EnfermeroController {

    private final EnfermeroService enfermeroService;

    @PostMapping("/create")
    public ResponseEntity<Response> registerEnfermero(@RequestBody EnfermeroDto enfermeroRequest) {
        return ResponseEntity.ok(enfermeroService.registerEnfermero(enfermeroRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllEnfermeros() {
        return ResponseEntity.ok(enfermeroService.get());
    }

    @GetMapping("/getByCi/{ci}")
    public ResponseEntity<Response> getEnfermeroByCi(@PathVariable Long ci) {
        return ResponseEntity.ok(enfermeroService.getByCi(ci));
    }

    @PutMapping("/update/{ci}")
    public ResponseEntity<Response> updateEnfermero(@PathVariable Long ci, @RequestBody EnfermeroDto enfermeroRequest) {
        return ResponseEntity.ok(enfermeroService.update(ci, enfermeroRequest));
    }

    @DeleteMapping("/delete/{ci}")
    public ResponseEntity<Response> deleteEnfermero(@PathVariable Long ci) {
        return ResponseEntity.ok(enfermeroService.delete(ci));
    }
}