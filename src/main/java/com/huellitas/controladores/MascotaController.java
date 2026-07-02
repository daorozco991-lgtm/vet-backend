package com.huellitas.controladores;

import com.huellitas.modelos.dto.MascotaDetailDto;
import com.huellitas.modelos.dto.MascotaDto;
import com.huellitas.modelos.dto.MascotaPatchDto;
import com.huellitas.projection.MascotaSelectProjection;
import com.huellitas.servicios.MascotaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity<MascotaDto> registrar(
            @Valid @RequestBody MascotaDto mascotaDto) {

        MascotaDto creada = mascotaService.registrar(mascotaDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    @GetMapping
    public ResponseEntity<Page<MascotaDetailDto>> consultar(Pageable pageable) {

        return ResponseEntity.ok(mascotaService.consultar(pageable));
    }

    @PatchMapping("/{mascotaId}")
    public ResponseEntity<MascotaDto> actualizar(
            @PathVariable Integer mascotaId,
            @Valid @RequestBody MascotaPatchDto mascotaPatchDto) {

        return ResponseEntity.ok(
                mascotaService.actualizar(mascotaId, mascotaPatchDto)
        );
    }

    @DeleteMapping("/{mascotaId}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer mascotaId) {
        mascotaService.eliminar(mascotaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MascotaDetailDto>> buscarPorNombreODueno(
            @RequestParam(name = "query", required = false, defaultValue = "") String busqueda, Pageable pageable) {

        return ResponseEntity.ok(
                mascotaService.buscarMascotaPorNombreODueno(busqueda, pageable)
        );
    }

    @GetMapping("id/{mascotaId}")
    public ResponseEntity<MascotaDto> buscarPorId(
            @PathVariable Integer mascotaId) {

        return ResponseEntity.ok(mascotaService.buscarPorId(mascotaId));
    }

    @GetMapping("/ids")
    public ResponseEntity<List<MascotaSelectProjection>> listarParaSelect() {

        List<MascotaSelectProjection> mascotas = mascotaService.listarIdsDeMascota();

        return ResponseEntity.ok(mascotas);
    }


}
