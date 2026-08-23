package com.dicampus.j2ee.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cancion")
public class CancionEntity implements Comparable<CancionEntity> {

    private long id;
    private String titulo;
    private float duracion;
    private String genero;
    private CantanteEntity autor;
    private DescripcionEntity descripcion;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public CantanteEntity getAutor() {
        return autor;
    }

    public void setAutor(CantanteEntity autor) {
        this.autor = autor;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public DescripcionEntity getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(DescripcionEntity descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int compareTo(CancionEntity o) {
        if (this.titulo == null || o == null || o.getTitulo() == null) {
            return 0;
        }
        return getTitulo().compareTo(o.getTitulo());
    }

    @Override
    public String toString() {
        return " Id :" + id + "\nTítulo:" + titulo + "\nDuración:" + duracion;
    }
}