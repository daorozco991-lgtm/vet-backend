package com.huellitas.modelos.mapas;

import com.huellitas.modelos.Dueno;
import com.huellitas.modelos.dto.DuenoDetailDto;
import com.huellitas.modelos.dto.DuenoDto;
import com.huellitas.modelos.dto.DuenoPatchDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {MascotaMapper.class})
public interface DuenoMapper {
    @Mapping(source = "nombre", target = "nombreDueno")
    DuenoDto toDto(Dueno dueno);

    @Mapping(source = "nombre", target = "nombreDueno")
    DuenoDetailDto toDuenoDetailDto(Dueno dueno);

    @InheritInverseConfiguration
    @Mapping(target = "mascotas", ignore = true)
    Dueno toEntity(DuenoDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mascotas", ignore = true)
    @Mapping(target = "nombre", source = "nombreDueno")
    void actualizarDesdeDto(DuenoPatchDto dto, @MappingTarget Dueno entidad);
}
