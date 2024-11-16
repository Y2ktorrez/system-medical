package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.PagoDto;

public interface PagoService {
    PagoDto calcularPagoTotal(Long idConsulta);
    PagoDto realizarPago(Long idConsulta, String tipoPago);

}
