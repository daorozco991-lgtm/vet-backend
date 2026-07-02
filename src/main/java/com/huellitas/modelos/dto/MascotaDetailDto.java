package com.huellitas.modelos.dto;

import java.time.LocalDate;
import java.util.List;

public class MascotaDetailDto {
    private Integer id;
    private String nombreDueno;
    private String nombreMascota;
    private List<CitaDto> citas;
    private String edad;
    private String raza;
    private LocalDate fechaNacimiento;

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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

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

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }


    public Integer getTotalCitas() {
        return citas != null ? citas.size() : 0;
    }

    public void setCitas(List<CitaDto> citas) {
        this.citas = citas;
    }
}
