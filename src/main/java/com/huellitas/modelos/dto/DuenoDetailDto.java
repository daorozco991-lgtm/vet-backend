package com.huellitas.modelos.dto;

import java.util.List;

public class DuenoDetailDto {
    private Integer id;
    private String nombreDueno;
    private String contacto;
    private List<MascotaDto> mascotas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Integer getTotalMascotas() {
        return mascotas != null ? mascotas.size() : 0;
    }

    public void setMascotas(List<MascotaDto> mascotas) {
        this.mascotas = mascotas;
    }
}
