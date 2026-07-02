package com.huellitas.servicios;

import com.huellitas.excepciones.NegocioException;
import com.huellitas.modelos.Dueno;
import com.huellitas.modelos.dto.DuenoDetailDto;
import com.huellitas.modelos.dto.DuenoDto;
import com.huellitas.modelos.dto.DuenoPatchDto;
import com.huellitas.modelos.mapas.DuenoMapper;
import com.huellitas.projection.DuenoSelectProjection;
import com.huellitas.projection.DuenoSelectRepository;
import com.huellitas.repositorios.DuenoRepository;
import com.huellitas.repositorios.MascotaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DuenoService {

    private final DuenoRepository duenoRepository;
    private final DuenoMapper duenoMapper;
    private final MascotaRepository mascotaRepository;
    private final DuenoSelectRepository duenoSelectRepository;

    public DuenoService(DuenoRepository duenoRepository, DuenoMapper duenoMapper, MascotaRepository mascotaRepository, DuenoSelectRepository duenoSelectRepository) {
        this.duenoRepository = duenoRepository;
        this.duenoMapper = duenoMapper;
        this.mascotaRepository = mascotaRepository;
        this.duenoSelectRepository = duenoSelectRepository;
    }

    public DuenoDto registrar(DuenoDto duenoPatchDto) {

        if (duenoRepository.existsById(duenoPatchDto.getId())) {
            throw new NegocioException("El dueño ya está registrado");
        }
        if (duenoRepository.existsByContacto(duenoPatchDto.getContacto())) {
            throw new NegocioException("Ya existe un dueño con ese contacto");
        }
        Dueno dueno = duenoMapper.toEntity(duenoPatchDto);
        return duenoMapper.toDto(duenoRepository.save(dueno));
    }


    public Page<DuenoDetailDto> consultar(Pageable pageable) {

        return duenoRepository.findAll(pageable).map(duenoMapper::toDuenoDetailDto);
    }


    @Transactional
    public DuenoDto actualizar(Integer mascotaId, DuenoPatchDto duenoPatchDto) {

        Dueno dueno = duenoRepository.findById(mascotaId).orElseThrow(() -> new NegocioException("No existe un dueño con id: " + mascotaId));

        duenoMapper.actualizarDesdeDto(duenoPatchDto, dueno);

        return duenoMapper.toDto(duenoRepository.save(dueno));
    }


    public void eliminar(Integer mascotaId) {

        Dueno dueno = duenoRepository.findById(mascotaId).orElseThrow(() -> new NegocioException("No existe un dueño con ese id"));

        if (mascotaRepository.existsByDuenoId(mascotaId)) {
            throw new NegocioException("No se puede eliminar el dueño porque tiene mascotas asociadas");
        }

        duenoRepository.delete(dueno);
    }

    public Page<DuenoDetailDto> buscarDuenoPorNombreOId(String busqueda, Pageable pageable) {
        if (busqueda == null || busqueda.trim().isEmpty()) {
            return consultar(pageable);
        }
        Page<Dueno> result = duenoRepository.buscarDuenoPorFiltro(busqueda, pageable);
        return result.map(duenoMapper::toDuenoDetailDto);

    }


    public List<DuenoSelectProjection> listarIdsDeDueno() {
        return duenoSelectRepository.findAllBy();
    }


}
