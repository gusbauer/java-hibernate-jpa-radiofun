// me salen etso falires Failure Trace
package com.dicampus.j2ee.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType; // Importación añadida
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ListaReproduccion")
public class ListaReproduccionEntity {

    private long id;
    private String nombre;
    private List<CancionEntity> canciones;

    @Id
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

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
        name = "ListaReproduccion_Cancion",
        joinColumns = @JoinColumn(name = "listasReproduccion_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "canciones_cancion_id", referencedColumnName = "cancion_id")
    )
    @OrderColumn(name = "indice_orden")
    public List<CancionEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionEntity> canciones) {
        this.canciones = canciones;
    }
}