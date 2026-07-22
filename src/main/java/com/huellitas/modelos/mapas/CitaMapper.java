package com.huellitas.modelos.mapas;

import com.huellitas.modelos.Cita;
import com.huellitas.modelos.dto.CitaDetailDto;
import com.huellitas.modelos.dto.CitaDto;
import com.huellitas.modelos.dto.CitaPatchDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(target = "mascotaId", source = "mascota.id")
    CitaDto toDto(Cita cita);

    @Mapping(source = "mascota.dueno.id", target = "duenoId")
    @Mapping(source = "mascota.nombre", target = "nombreMascota")
    @Mapping(source = "mascota.dueno.nombre", target = "nombreDueno")
    @Mapping(source = "mascota.dueno.contacto", target = "contacto")
    CitaDetailDto toCitaDetailDto(Cita cita);

    @InheritInverseConfiguration
    Cita toEntity(CitaDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "mascota", ignore = true)
    @Mapping(target = "id", ignore = true)
    void actualizarDesdeDto(CitaPatchDto dto, @MappingTarget Cita cita);
}
