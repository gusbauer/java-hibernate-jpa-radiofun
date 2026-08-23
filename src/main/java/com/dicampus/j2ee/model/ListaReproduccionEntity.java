// me sogue dando error pasame el codigo complto
package com.dicampus.j2ee.model;

import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.Table;

@Entity
@Table(name = "ListaReproduccion")
public class ListaReproduccionEntity {

    private long id;
    private String nombre;
    private Map<String, CancionEntity> canciones;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @MapKey(name = "titulo")
    public Map<String, CancionEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(Map<String, CancionEntity> canciones) {
        this.canciones = canciones;
    }
}