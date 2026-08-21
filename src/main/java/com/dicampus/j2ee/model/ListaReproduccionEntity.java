package com.dicampus.j2ee.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ListaReproduccion")
public class ListaReproduccionEntity {

    private long id;
    private String nombre;
    private Set<CancionEntity> canciones = new HashSet<>();

    public ListaReproduccionEntity() {}

    @Id
    @Column(name = "lista_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "nombre", length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "lista_cancion",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    public Set<CancionEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<CancionEntity> canciones) {
        this.canciones = canciones;
    }
}