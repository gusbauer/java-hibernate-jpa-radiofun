package com.dicampus.j2ee.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cancion")
public class CancionEntity {

    private long id;
    private String titulo;
    private CantanteEntity autor;
    private float duracion;
    private String genero;
    private DescripcionEntity descripcion;
    private Set<ListaReproduccionEntity> listasReproduccion = new HashSet<>();

    public CancionEntity() {}

    @Id
    @Column(name = "cancion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "titulo", nullable = false, length = 125)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @ManyToOne
    @JoinColumn(name = "autor_id")
    public CantanteEntity getAutor() {
        return autor;
    }

    public void setAutor(CantanteEntity autor) {
        this.autor = autor;
    }

    @Column(name = "duracion", nullable = false)
    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    @Column(name = "genero", nullable = false, length = 30)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @OneToOne
    @JoinColumn(name = "descripcion_id")
    public DescripcionEntity getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(DescripcionEntity descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToMany(mappedBy = "canciones")
    public Set<ListaReproduccionEntity> getListasReproduccion() {
        return listasReproduccion;
    }

    public void setListasReproduccion(Set<ListaReproduccionEntity> listasReproduccion) {
        this.listasReproduccion = listasReproduccion;
    }
}