package com.huellitas.modelos.auth;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El usuario es requerido")
    private String userName;

    @NotBlank(message = "La contraseña es requerida")
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
