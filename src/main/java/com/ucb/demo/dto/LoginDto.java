package com.ucb.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = "El email es obligatorio")
    @jakarta.validation.constraints.Email(message = "El email debe ser válido")
    private String email;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
 public LoginDto(String email, String password) {
     this.email = email;
     this.password = password;
 }
 
 public String getEmail() {
     return email;
 }
 public String getPassword() {
     return password;
 }

 public void setName(String email) {
    this.email = email;
 }
 public void setPrice(String password) {
    this.password = password;
 }
}
