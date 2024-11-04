package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.Response;
import com.y2k.hospital.dto.TipoPagoDto;

public interface TipoPagoService {
    Response createTipoPago(TipoPagoDto TipoPagoDto);
    Response getTipoPagoById(Long id);
    Response getAllTipoPago();
    Response updateTipoPago(Long id, TipoPagoDto TipoPagoDto);
    Response deleteTipoPago(Long id);
}
