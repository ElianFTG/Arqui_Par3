package com.ucb.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CardDto {
    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(regexp = "\\d{16}", message = "El número de tarjeta debe tener 16 dígitos")
    private String card_number;
    @NotBlank(message = "La fecha de expiración es obligatoria")
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "La fecha de expiración debe tener el formato MM/YY")
    private String exp_date;
    @jakarta.validation.constraints.NotBlank(message = "El CVV es obligatorio")
    @jakarta.validation.constraints.Pattern(regexp = "\\d{3}", message = "El CVV debe tener 3 dígitos")
    private String cvv;
 public CardDto(String card_number, String cvv, String exp_date) {
     this.card_number = card_number;
     this.exp_date = exp_date;
     this.cvv = cvv;
 }
 
 public String getCard_num() {
     return card_number;
 }
 public String getEx_Date() {
     return exp_date;
 }
 public String getCvv() {
     return cvv;
 }
 public void setCard_num(String card_number) {
     this.card_number = card_number;
 }
 public void setEx_Date(String exp_date) {
     this.exp_date = exp_date;
 }
 public void setCvv(String cvv) {
     this.cvv = cvv;
 }
}
