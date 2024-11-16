package com.y2k.hospital.controller;

import com.y2k.hospital.dto.PagoDto;
import com.y2k.hospital.service.interf.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Endpoint para calcular el pago total
    @GetMapping("/calcular/{idConsulta}")
    public ResponseEntity<PagoDto> calcularPagoTotal(@PathVariable Long idConsulta) {
        try {
            PagoDto pagoDto = pagoService.calcularPagoTotal(idConsulta);
            return ResponseEntity.ok(pagoDto);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Endpoint para realizar el pago de la consulta
    @PostMapping("/pagar/{idConsulta}")
    public ResponseEntity<PagoDto> pagarConsulta(@PathVariable Long idConsulta,
                                                 @RequestParam String tipoPago) {
        try {
            // Llamar al servicio para realizar el pago, pasando el tipo de pago
            PagoDto pagoDto = pagoService.realizarPago(idConsulta, tipoPago);
            return ResponseEntity.ok(pagoDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
