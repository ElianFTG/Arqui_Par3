package com.example.COBO;

import jakarta.validation.constraints.NotBlank;


public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")
    @jakarta.validation.constraints.Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
