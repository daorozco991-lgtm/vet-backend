package com.huellitas.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Duenos")
public class Dueno {

    @Id
    @Column(name = "id_dueno")
    private Integer id;

    @Column(name = "nombre_dueno")
    private String nombre;

    @Column(name = "contacto")
    private String contacto;

    @OneToMany(mappedBy = "dueno")
    private List<Mascota> mascotas;

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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
