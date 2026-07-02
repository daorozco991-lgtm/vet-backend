package com.huellitas.servicios;


import com.huellitas.excepciones.NegocioException;
import com.huellitas.modelos.Cita;
import com.huellitas.modelos.Mascota;
import com.huellitas.modelos.dto.CitaDetailDto;
import com.huellitas.modelos.dto.CitaDto;
import com.huellitas.modelos.dto.CitaPatchDto;
import com.huellitas.modelos.mapas.CitaMapper;
import com.huellitas.repositorios.CitaRepository;
import com.huellitas.repositorios.MascotaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final CitaMapper citaMapper;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, CitaMapper citaMapper) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.citaMapper = citaMapper;
    }

    public CitaDto registrar(CitaDto citaDto) {

        Mascota mascota = mascotaRepository.findById(citaDto.getMascotaId()).orElseThrow(() ->
                new NegocioException("No existe una mascota con ese id"));
        Cita cita = citaMapper.toEntity(citaDto);
        cita.setMascota(mascota);

        return citaMapper.toDto(citaRepository.save(cita));
    }

    @Transactional(readOnly = true)
    public Page<CitaDetailDto> consultar(Pageable pageable) {
        return citaRepository.findAll(pageable).map(citaMapper::toCitaDetailDto);
    }

    public CitaDto actualizar(Integer id, CitaPatchDto dto) {

        Cita cita = citaRepository.findById(id).orElseThrow(() ->
                new NegocioException("No existe una cita con ese id"));

        citaMapper.actualizarDesdeDto(dto, cita);

        return citaMapper.toDto(citaRepository.save(cita));
    }

    public void eliminar(Integer citaId) {

        Cita cita = citaRepository.findById(citaId).orElseThrow(() ->
                new NegocioException("No existe una cita con ese id"));
        citaRepository.deleteById(citaId);
    }

    public Page<CitaDetailDto> buscarMascotaPorNombreODueno(String busqueda, Pageable pageable) {
        if (busqueda == null || busqueda.trim().isEmpty()) {
            return consultar(pageable);
        }
        Page<Cita> result = citaRepository.buscarCitasPorNombreMascotaOIdDueno(busqueda, pageable);
        return result.map(citaMapper::toCitaDetailDto);

    }


}
