package com.huellitas.modelos.dto;

import java.time.LocalDate;

public class MascotaPatchDto {
    private String nombreMascota;

    private String raza;

    private LocalDate fechaNacimiento;

    public String getNombreMasccota() {
        return nombreMascota;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setNombreMasccota(String nombreMasccota) {
        this.nombreMascota = nombreMasccota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}