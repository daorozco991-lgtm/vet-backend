package com.huellitas.servicios;

import com.huellitas.excepciones.NegocioException;
import com.huellitas.modelos.Dueno;
import com.huellitas.modelos.Mascota;
import com.huellitas.modelos.dto.MascotaDetailDto;
import com.huellitas.modelos.dto.MascotaDto;
import com.huellitas.modelos.dto.MascotaPatchDto;
import com.huellitas.modelos.mapas.MascotaMapper;
import com.huellitas.projection.MascotaSelectProjection;
import com.huellitas.projection.MascotaSelectRepository;
import com.huellitas.repositorios.DuenoRepository;
import com.huellitas.repositorios.MascotaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;
    private final DuenoRepository duenoRepository;

    private final MascotaSelectRepository mascotaSelectRepository;

    public MascotaService(MascotaRepository mascotaRepository, MascotaMapper mascotaMapper, DuenoRepository duenoRepository, MascotaSelectRepository mascotaSelectRepository) {
        this.mascotaRepository = mascotaRepository;
        this.mascotaMapper = mascotaMapper;
        this.duenoRepository = duenoRepository;
        this.mascotaSelectRepository = mascotaSelectRepository;
    }

    public MascotaDto registrar(MascotaDto mascotaDto) {

        Dueno dueno = duenoRepository.findById(mascotaDto.getDuenoId()).orElseThrow(() -> new NegocioException("No existe un dueño con ese id"));

        Mascota mascota = mascotaMapper.toEntity(mascotaDto);
        mascota.setDueno(dueno);

        Mascota guardada = mascotaRepository.save(mascota);

        return mascotaMapper.toDto(guardada);
    }


    @Transactional(readOnly = true)
    public Page<MascotaDetailDto> consultar(Pageable pageable) {
        return mascotaRepository.findAll(pageable).map(mascotaMapper::toMascotaDetailDto);
    }


    public MascotaDto actualizar(Integer mascotaId, MascotaPatchDto mascotaPatchDto) {
        LocalDate fechaUsuario = LocalDate.now();
        long diferenciaAnios = ChronoUnit.YEARS.between(mascotaPatchDto.getFechaNacimiento(), fechaUsuario);
        if (diferenciaAnios > 100) {
            throw new NegocioException("El paciente no puede exceder 100 años de edad");
        }
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow(() -> new NegocioException("La mascota con ese id no existe"));

        mascotaMapper.actualizarDesdeDto(mascotaPatchDto, mascota);


        Dueno dueno = duenoRepository.findById(mascota.getDueno().getId()).orElseThrow(() -> new NegocioException("No existe un dueño con ese id"));

        mascota.setDueno(dueno);


        return mascotaMapper.toDto(mascota);
    }

    public void eliminar(Integer mascotaId) {

        if (!mascotaRepository.existsById(mascotaId)) {
            throw new NegocioException("No existe una mascota con ese id");
        }
        mascotaRepository.deleteById(mascotaId);
    }

    public Page<MascotaDetailDto> buscarMascotaPorNombreODueno(String busqueda, Pageable pageable) {
        if (busqueda == null || busqueda.trim().isEmpty()) {
            return consultar(pageable);
        }
        Page<Mascota> result = mascotaRepository.buscarMascotaPorFiltro(busqueda, pageable);
        return result.map(mascotaMapper::toMascotaDetailDto);

    }

    public MascotaDto buscarPorId(Integer mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow(() -> new NegocioException("Error la mascota no existe"));

        return mascotaMapper.toDto(mascota);

    }

    public List<MascotaSelectProjection> listarIdsDeMascota() {
        return mascotaSelectRepository.findAllBy();
    }


}
