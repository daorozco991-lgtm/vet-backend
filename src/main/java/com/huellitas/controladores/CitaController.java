package com.huellitas.controladores;

import com.huellitas.modelos.dto.CitaDetailDto;
import com.huellitas.modelos.dto.CitaDto;
import com.huellitas.modelos.dto.CitaPatchDto;
import com.huellitas.servicios.CitaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<CitaDto> registrar(@Valid @RequestBody CitaDto citaDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.registrar(citaDto));
    }


    @GetMapping
    public ResponseEntity<Page<CitaDetailDto>> consultar(Pageable pageable) {

        return ResponseEntity.ok(citaService.consultar(pageable));
    }


    @PatchMapping("/{citaId}")
    public ResponseEntity<CitaDto> actualizar(@PathVariable Integer citaId, @Valid @RequestBody CitaPatchDto citaPatchDto) {

        return ResponseEntity.ok(citaService.actualizar(citaId, citaPatchDto));
    }

    @DeleteMapping("/{citaId}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer citaId) {

        citaService.eliminar(citaId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CitaDetailDto>> buscarPorNombreODueno(@RequestParam(name = "query", required = false, defaultValue = "") String busqueda, Pageable pageable) {

        return ResponseEntity.ok(citaService.buscarMascotaPorNombreODueno(busqueda, pageable));
    }


}