package com.huellitas.repositorios;

import com.huellitas.modelos.Dueno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer> {

    @Query("SELECT d FROM Dueno d WHERE " + "LOWER(d.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " + "CAST(d.id AS string) LIKE CONCAT('%', :busqueda, '%')")
    Page<Dueno> buscarDuenoPorFiltro(@Param("busqueda") String busqueda, Pageable pageable);


    boolean existsByContacto(String contacto);
}
