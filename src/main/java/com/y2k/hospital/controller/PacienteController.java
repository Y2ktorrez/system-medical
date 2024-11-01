package com.y2k.hospital.controller;

import com.y2k.hospital.dto.PacienteDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.service.interf.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PreAuthorize("hasAnyRole('Administrador', 'Medico', 'Enfermero')")
    @PostMapping("/create")
    public ResponseEntity<Response> registerPaciente(@RequestBody PacienteDto pacienteRequest) {
        return ResponseEntity.ok(pacienteService.registerPaciente(pacienteRequest));
    }

    @PreAuthorize("hasAnyRole('Administrador', 'Medico', 'Enfermero')")
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.getAll());
    }

    @PreAuthorize("hasAnyRole('Administrador', 'Medico', 'Enfermero')")
    @GetMapping("/getByCi/{ci}")
    public ResponseEntity<Response> getPacienteByCi(@PathVariable Long ci) {
        return ResponseEntity.ok(pacienteService.getByCi(ci));
    }
}