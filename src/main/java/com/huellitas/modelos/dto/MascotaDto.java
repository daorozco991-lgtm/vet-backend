package com.huellitas.modelos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class MascotaDto {
    private Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreMascota;

    @NotBlank(message = "La raza es obligatoria")
    private String raza;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El dueño es obligatorio")
    private Integer duenoId;

    private String edad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(Integer duenoId) {
        this.duenoId = duenoId;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
