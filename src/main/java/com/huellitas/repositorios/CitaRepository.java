package com.huellitas.repositorios;

import com.huellitas.modelos.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    @Query("""
            SELECT c FROM Cita c\s
            WHERE LOWER(c.mascota.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))\s
               OR LOWER(c.mascota.dueno.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))\s
               OR CAST(c.mascota.id AS string) LIKE CONCAT('%', :busqueda, '%')
               OR CAST(c.mascota.dueno.id AS string) = :busqueda
            """)
    Page<Cita> buscarCitasPorNombreMascotaOIdDueno(@Param("busqueda") String busqueda, Pageable pageable);
}