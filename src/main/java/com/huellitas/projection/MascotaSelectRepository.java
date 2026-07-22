package com.huellitas.projection;

import com.huellitas.modelos.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaSelectRepository extends JpaRepository<Mascota, Integer> {

    List<MascotaSelectProjection> findAllBy();

}