package com.example.batch2;

public class PersonaDTO {
    private long id;
    private String nombre;
    private String correo;
    private String ip;

    // Constructor to match the required parameters
    public PersonaDTO(long id, String nombre, String correo, String ip) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.ip = ip;
    }

    // Getters and setters (optional, if needed)
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