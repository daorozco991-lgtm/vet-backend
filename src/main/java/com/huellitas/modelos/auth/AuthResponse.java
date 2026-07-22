package com.huellitas.modelos.auth;


public class AuthResponse {
    private String token;
    private String userName;
    private String name;
    private String rol;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AuthResponse(String token, String userName, String name, String rol) {
        this.token = token;
        this.userName = userName;
        this.name = name;
        this.rol = rol;
    }

    public AuthResponse() {
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
