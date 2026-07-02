package com.huellitas.modelos.mapas;

import com.huellitas.modelos.Mascota;
import com.huellitas.modelos.dto.MascotaDetailDto;
import com.huellitas.modelos.dto.MascotaDto;
import com.huellitas.modelos.dto.MascotaPatchDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CitaMapper.class})
public interface MascotaMapper {

    @Mapping(source = "dueno.id", target = "duenoId")
    @Mapping(source = "nombre", target = "nombreMascota")
    MascotaDto toDto(Mascota mascota);

    @Mapping(source = "dueno.nombre", target = "nombreDueno")
    @Mapping(source = "nombre", target = "nombreMascota")
    MascotaDetailDto toMascotaDetailDto(Mascota mascota);

    @InheritInverseConfiguration
    @Mapping(target = "citas", ignore = true)
    Mascota toEntity(MascotaDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dueno", ignore = true)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "nombre", source = "nombreMascota")
    void actualizarDesdeDto(MascotaPatchDto dto, @MappingTarget Mascota entidad);
}