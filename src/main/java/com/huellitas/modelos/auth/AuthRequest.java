package com.huellitas.modelos.auth;

import jakarta.validation.constraints.NotBlank;


public class AuthRequest {

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
}
