package com.y2k.hospital.controller;

import com.y2k.hospital.dto.EspecialidadDto;
import com.y2k.hospital.dto.Response;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.service.additional.BitacoraService;
import com.y2k.hospital.service.interf.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService especialidadService;
    private final BitacoraService bitacoraService;

    @PostMapping("/create")
    public ResponseEntity<Response> createEspecialidad(@RequestBody EspecialidadDto especialidadDto) {
        Response response = especialidadService.createEspecialidad(especialidadDto);

        //User usuarioActual = bitacoraService.obtenerUsuarioActual();
        //bitacoraService.registrarAccion("Creo una Especialidad", usuarioActual);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Response> getEspecialidadById(@PathVariable Long id) {
        Response response = especialidadService.getEspecialidadById(id);

        //User usuarioActual = bitacoraService.obtenerUsuarioActual();
        //bitacoraService.registrarAccion("Visualizo una Especialidad" + id, usuarioActual);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllEspecialidades() {
        Response response = especialidadService.getAllEspecialidades();

        //User usuarioActual = bitacoraService.obtenerUsuarioActual();
        //bitacoraService.registrarAccion("Visualizo todas las Especialidades", usuarioActual);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateEspecialidad(@PathVariable Long id,
                                                       @RequestBody EspecialidadDto especialidadDto) {
        Response response = especialidadService.updateEspecialidad(id, especialidadDto);

        //User usuarioActual = bitacoraService.obtenerUsuarioActual();
        //bitacoraService.registrarAccion("Actualizo una Especialidad" + id, usuarioActual);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteEspecialidad(@PathVariable Long id) {
        Response response = especialidadService.deleteEspecialidad(id);

        //User usuarioActual = bitacoraService.obtenerUsuarioActual();
        //bitacoraService.registrarAccion("Elimino una Especialidad" + id, usuarioActual);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
