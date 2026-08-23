package com.dicampus.j2ee.model;

import java.util.SortedSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.SortComparator;

@Entity
@Table(name = "ListaReproduccion")
public class ListaReproduccionEntity {

    private long id;
    private String nombre;
    private SortedSet<CancionEntity> canciones;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @SortComparator(CancionEntityComparatorByCantante.class)
    @ManyToMany(cascade = CascadeType.PERSIST)
    public SortedSet<CancionEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(SortedSet<CancionEntity> canciones) {
        this.canciones = canciones;
    }
}