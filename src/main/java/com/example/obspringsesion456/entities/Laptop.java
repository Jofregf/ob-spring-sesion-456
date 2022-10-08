package com.example.obspringsesion456.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String procesador;
    private Double pantalla;
    private Integer almacenamiento;
    private Integer ram;
    private Double precio;
    private Boolean windows;

    public Laptop() {
    }

    public Laptop(Long id, String marca, String modelo, String procesador, Double pantalla, Integer almacenamiento, Integer ram, Double precio, Boolean windows) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.procesador = procesador;
        this.pantalla = pantalla;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.precio = precio;
        this.windows = windows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Double getPantalla() {
        return pantalla;
    }

    public void setPantalla(Double pantalla) {
        this.pantalla = pantalla;
    }

    public Integer getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(Integer almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getWindows() {
        return windows;
    }

    public void setWindows(Boolean windows) {
        this.windows = windows;
    }
}
