package com.example.batch2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Persona {
    private long id;
    private String nombre;
    private String correo;
    private String ip;

    // Constructor para inicializar los parámetros requeridos
    public Persona(long id, String nombre, String correo, String ip) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.ip = ip;
    }

    // Getters y setters (opcional, si se necesitan)
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}