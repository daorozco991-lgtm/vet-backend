package com.huellitas.controladores;

import com.huellitas.modelos.dto.DuenoDetailDto;
import com.huellitas.modelos.dto.DuenoDto;
import com.huellitas.modelos.dto.DuenoPatchDto;
import com.huellitas.projection.DuenoSelectProjection;
import com.huellitas.servicios.DuenoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/duenos")
public class DuenoController {

    private final DuenoService duenoService;

    public DuenoController(DuenoService duenoService) {
        this.duenoService = duenoService;
    }


    @PostMapping
    public ResponseEntity<DuenoDto> registrar(@Valid @RequestBody DuenoDto duenoDto) {

        DuenoDto creado = duenoService.registrar(duenoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }


    @GetMapping
    public ResponseEntity<Page<DuenoDetailDto>> consultar(Pageable pageable) {

        Page<DuenoDetailDto> duenos = duenoService.consultar(pageable);

        return ResponseEntity.ok(duenos);
    }


    @PatchMapping("/{duenoId}")
    public ResponseEntity<DuenoDto> actualizar(@PathVariable Integer duenoId, @Valid @RequestBody DuenoPatchDto duenoPatchDto) {

        return ResponseEntity.ok(duenoService.actualizar(duenoId, duenoPatchDto));
    }


    @DeleteMapping("/{duenoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer duenoId) {

        duenoService.eliminar(duenoId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DuenoDetailDto>> buscarPorNombreODueno(@RequestParam(name = "query", required = false, defaultValue = "") String busqueda, Pageable pageable) {

        return ResponseEntity.ok(duenoService.buscarDuenoPorNombreOId(busqueda, pageable));
    }

    @GetMapping("/ids")
    public ResponseEntity<List<DuenoSelectProjection>> listarParaSelect() {

        List<DuenoSelectProjection> duenos = duenoService.listarIdsDeDueno();

        return ResponseEntity.ok(duenos);
    }
}
