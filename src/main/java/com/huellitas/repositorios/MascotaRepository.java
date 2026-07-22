package com.huellitas.repositorios;

import com.huellitas.modelos.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    @Query("""
            SELECT m FROM Mascota m\s
            WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))\s
               OR LOWER(m.dueno.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))\s
               OR CAST(m.id AS string) LIKE CONCAT('%', :busqueda, '%')\s
               OR CAST(m.dueno.id AS string) = :busqueda
            """)
    Page<Mascota> buscarMascotaPorFiltro(@Param("busqueda") String busqueda, Pageable pageable);

    boolean existsByDuenoId(Integer idDueno);


}
