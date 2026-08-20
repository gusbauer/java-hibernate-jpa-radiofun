package com.dicampus.j2ee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cancion")
public class CancionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancion_id")
    private long id;

    @Column(name = "titulo", nullable = false, length = 125)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 80)
    private String autor;

    @Column(name = "duración", nullable = false)
    private float duracion;

    @Column(name = "genero", nullable = false, length = 30)
    private String genero;

    // Relación OneToOne con DescripcionEntity
    @OneToOne
    @JoinColumn(name = "descripcion_id")
    private DescripcionEntity descripcion;

    // Relación ManyToOne con CantanteEntity
    @ManyToOne
    @JoinColumn(name = "cantante_id")
    private CantanteEntity cantante;

    public CancionEntity() {}

    // Getters y Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public float getDuracion() { return duracion; }
    public void setDuracion(float duracion) { this.duracion = duracion; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public DescripcionEntity getDescripcion() { return descripcion; }
    public void setDescripcion(DescripcionEntity descripcion) { this.descripcion = descripcion; }

    public CantanteEntity getCantante() { return cantante; }
    public void setCantante(CantanteEntity cantante) { this.cantante = cantante; }
}