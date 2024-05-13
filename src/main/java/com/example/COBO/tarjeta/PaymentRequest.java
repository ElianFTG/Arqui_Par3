package com.example.COBO.tarjeta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PaymentRequest {

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(regexp = "\\d{16}", message = "El número de tarjeta debe tener 16 dígitos")
    private String cardNumber;

    @jakarta.validation.constraints.NotBlank(message = "El CVV es obligatorio")
    @jakarta.validation.constraints.Pattern(regexp = "\\d{3}", message = "El CVV debe tener 3 dígitos")
    private String cvv;

    @NotBlank(message = "La fecha de expiración es obligatoria")
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "La fecha de expiración debe tener el formato MM/YY")
    private String expirationDate;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
