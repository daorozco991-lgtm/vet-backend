package com.huellitas.modelos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DuenoDto {
    @NotNull(message = "El id es obligatorio")
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreDueno;

    @NotBlank(message = "El contacto es obligatorio")
    private String contacto;

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
}
