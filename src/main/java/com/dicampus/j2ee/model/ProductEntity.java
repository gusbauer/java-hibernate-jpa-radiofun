package com.dicampus.j2ee.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(
    name = "Product",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_product_nom_mar",
        columnNames = {"nombre", "marca"}
    )
)
@Check(constraints = "precio > 0")
public class ProductEntity {

    private long id;
    private String nombre;
    private float precio;
    private Date fechaCaducidad;
    private String marca;
    private boolean empaquetado;

    @Id
    @Column(name = "producto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "nombre", length = 80)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Column(name = "marca", length = 80)
    @ColumnDefault(value = "'Genérico'")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isEmpaquetado() {
        return empaquetado;
    }

    public void setEmpaquetado(boolean empaquetado) {
        this.empaquetado = empaquetado;
    }
}