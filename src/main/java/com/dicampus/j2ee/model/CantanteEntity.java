package com.dicampus.j2ee.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Cantante")
public class CantanteEntity {

    private long id;
    private String nombre;
    private Date fechaNacimiento;
    private String genero;
    private Boolean isGroup;
    private Set<CancionEntity> canciones = new HashSet<>();

    public CantanteEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cantante_id")
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

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "genero", length = 50)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Column(name = "es_grupo")
    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    // Mapeo One-To-Many apuntando a la propiedad 'cantante' en CancionEntity
    @OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<CancionEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<CancionEntity> canciones) {
        this.canciones = canciones;
    }

    // Método de conveniencia para añadir canciones
    public void addCancion(CancionEntity cancion) {
        canciones.add(cancion);
        cancion.setCantante(this);
    }
}