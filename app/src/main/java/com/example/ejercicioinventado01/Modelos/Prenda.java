package com.example.ejercicioinventado01.Modelos;

import java.io.Serializable;

public class Prenda implements Serializable{
    private String nombre;
    private int talla;
    private String estacion;
    private String color;

    public Prenda() {
    }

    public Prenda(String nombre, int talla, String estacion, String color) {
        this.nombre = nombre;
        this.talla = talla;
        this.estacion = estacion;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Prenda{" +
                "nombre='" + nombre + '\'' +
                ", talla=" + talla +
                ", estacion='" + estacion + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
