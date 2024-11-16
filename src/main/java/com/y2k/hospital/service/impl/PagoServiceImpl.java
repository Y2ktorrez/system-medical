package com.y2k.hospital.service.impl;

import com.y2k.hospital.Enum.TipoPago;
import com.y2k.hospital.dto.PagoDto;
import com.y2k.hospital.entity.*;
import com.y2k.hospital.repository.*;
import com.y2k.hospital.service.interf.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private InsumoTratamientoRepository insumoTratamientoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private AnalisisRepository analisisRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public PagoDto calcularPagoTotal(Long idConsulta) {
        // Obtener la consulta
        Consulta consulta = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));

        // Inicializar el costo total
        Integer costoTotal = 0;

        // Calcular el costo de los tratamientos e insumos
        List<Tratamiento> tratamientos = tratamientoRepository.findByConsultaId(idConsulta);
        for (Tratamiento tratamiento : tratamientos) {
            List<InsumoTratamiento> insumos = insumoTratamientoRepository.findByTratamientoId(tratamiento.getId());
            for (InsumoTratamiento insumo : insumos) {
                costoTotal += insumo.getCostoTotal() * insumo.getCantidad();
            }
        }

        // Calcular el costo de los análisis
        List<Analisis> analisisList = analisisRepository.findByConsultaId(idConsulta);  // Asegúrate de tener el repositorio para Analisis
        for (Analisis analisis : analisisList) {
            costoTotal += analisis.getTipoAnalisis().getCosto();  // Sumamos el costo del tipo de análisis
        }

        // Calcular el costo de los exámenes
        List<Examen> examenList = examenRepository.findByConsultaId(idConsulta);  // Asegúrate de tener el repositorio para Examen
        for (Examen examen : examenList) {
            costoTotal += examen.getTipoExamen().getCosto();  // Sumamos el costo del tipo de examen
        }

        // Crear el PagoDto con el costo total
        PagoDto pagoDto = new PagoDto();
        pagoDto.setIdConsulta(idConsulta);
        pagoDto.setCostoTotal(costoTotal);
        pagoDto.setCancelado(false);  // Establecer si está cancelado (puedes agregar tu lógica aquí)
        pagoDto.setTipoPago("Efectivo");  // Establecer el tipo de pago (esto se puede personalizar)

        return pagoDto;
    }

    @Override
    public PagoDto realizarPago(Long idConsulta, String tipoPago) {
        // Obtener la consulta para la que se va a realizar el pago
        Consulta consulta = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));

        // Calcular el costo total de la consulta, ahora incluye tratamientos, insumos, análisis y exámenes
        PagoDto pagoDto = calcularPagoTotal(idConsulta);

        // Convertir el tipoPago de String a Enum TipoPago
        TipoPago tipoPagoEnum;
        try {
            tipoPagoEnum = TipoPago.valueOf(tipoPago);  // Convertir el String a Enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de pago no válido: " + tipoPago);
        }

        // Crear un nuevo objeto de Pago
        Pago pago = new Pago();
        pago.setConsulta(consulta);
        pago.setTipoPago(tipoPagoEnum);  // Establecer el tipo de pago como Enum
        pago.setCostoTotal(pagoDto.getCostoTotal());
        pago.setCancelado(false);  // Puedes actualizar el estado si es necesario

        // Guardar el pago en la base de datos
        pagoRepository.save(pago);

        // Devuelvo el PagoDto con la información del pago realizado
        pagoDto.setId(pago.getId());  // Asignar el ID del pago recién creado
        return pagoDto;
    }
}
