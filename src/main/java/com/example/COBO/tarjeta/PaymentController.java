package com.example.COBO.tarjeta;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import io.sentry.Sentry;

@RestController
public class PaymentController implements IPaymentApi{
    

    @PostMapping("/processPayment")
     public String index() {

        try {
            throw new Exception("This is a test.");
          } catch (Exception e) {
            Sentry.captureException(e);
          }
        return "Greetings from Spring boot";
    }
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {

           if ("123".equals(request.getCvv())) {
            // Pago exitoso
            PaymentResponse response = new PaymentResponse();
            response.setStatusCode(0);
            response.setMessage("successful");
            return ResponseEntity.ok().body(response);
        } else {
            // Pago no exitoso
            PaymentResponse response = new PaymentResponse();
            response.setStatusCode(1); // Código de estado para pago fallido
            response.setMessage("payment failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        
    }
    
    
}
    
