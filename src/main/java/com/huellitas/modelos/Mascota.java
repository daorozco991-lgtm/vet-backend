package com.huellitas.modelos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "Mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Integer id;

    @Column(name = "nombre_mascota")
    private String nombre;

    @Column(nullable = false)
    private String raza;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;


    @ManyToOne
    @JoinColumn(name = "id_dueno")
    private Dueno dueno;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Cita> citas;

    @Transient
    public String getEdad() {
        if (this.fechaNacimiento == null) return "N/A";

        Period periodo = Period.between(this.fechaNacimiento, LocalDate.now());

        if (periodo.getYears() > 0) {
            return periodo.getYears() + " años";
        } else if (periodo.getMonths() > 0) {
            return periodo.getMonths() + " meses";
        } else {
            return periodo.getDays() + " días";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
