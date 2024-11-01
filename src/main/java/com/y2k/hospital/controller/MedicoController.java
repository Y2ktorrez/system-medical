package com.y2k.hospital.controller;

import com.y2k.hospital.dto.MedicoDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping("/create")
    public ResponseEntity<Response> registerMedico(@RequestBody MedicoDto medicoRequest) {
        return ResponseEntity.ok(medicoService.registerMedico(medicoRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllMedicos() {
        return ResponseEntity.ok(medicoService.get());
    }

    @GetMapping("/getbyCi/{ci}")
    public ResponseEntity<Response> getMedicoByCi(@PathVariable Long ci) {
        return ResponseEntity.ok(medicoService.getByCi(ci));
    }

    @PutMapping("/update/{ci}")
    public ResponseEntity<Response> updateMedico(@PathVariable Long ci, @RequestBody MedicoDto medicoRequest) {
        return ResponseEntity.ok(medicoService.update(ci, medicoRequest));
    }

    @DeleteMapping("/delete/{ci}")
    public ResponseEntity<Response> deleteMedico(@PathVariable Long ci) {
        return ResponseEntity.ok(medicoService.delete(ci));
    }
}
