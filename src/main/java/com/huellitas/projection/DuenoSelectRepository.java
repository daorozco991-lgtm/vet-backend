package com.huellitas.projection;

import com.huellitas.modelos.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DuenoSelectRepository extends JpaRepository<Dueno, Integer> {

    List<DuenoSelectProjection> findAllBy();

}