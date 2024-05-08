package com.ucb.demo;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.ucb.demo.dto.CardDto;
import com.ucb.demo.dto.CardResponse;
import com.ucb.demo.dto.LoginDto;
import com.ucb.demo.dto.LoginToDto;
import com.ucb.demo.dto.ProductDto;

import io.sentry.Sentry;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController implements IHelloApi {
    
    @GetMapping("/")
    public String index() {

        try {
            throw new Exception("This is a test.");
          } catch (Exception e) {
            Sentry.captureException(e);
          }
        return "Greetings from Spring boot";
    }

    @GetMapping( value = "/products/{id}", produces = "application/json")
    public ResponseEntity<ProductDto> obtain(@PathVariable String id) {
        
        var product = new ProductDto(1,"a", 2);
        return ResponseEntity.ok(product);
    }
    
    @PostMapping( value = "/products", produces = "application/json")
    public ResponseEntity create(@RequestBody ProductDto product) {

        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(product);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema jsonSchema = factory.getSchema(HelloController.class.getClassLoader().getResourceAsStream("schemas/product.json"));
            JsonNode jsonNode = mapper.readTree(json);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode); 

            String errorsCombined = "";
            for( ValidationMessage error: errors) {
                errorsCombined += error.toString() +  "\n";
            }

            if(errors.size() > 0) {
                return ResponseEntity.badRequest().body("Please fix your JSON!,\n"+errorsCombined);
            }
            return ResponseEntity.ok(product);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.ok(product);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginToDto> login(@Valid @RequestBody LoginDto request) {
        String token = generateJWTToken();
        String timestamp = LocalDateTime.now().toString();

        LoginToDto response = new LoginToDto();
        response.setToken(token);
        response.setTime(timestamp);

        return ResponseEntity.ok().body(response);
    }

    private String generateJWTToken() {
        return "token_generado";
    }
    
    @PostMapping("/processPayment")
    public ResponseEntity<CardResponse> processPayment(@RequestBody CardDto request) {
           if ("123".equals(request.getCvv())) {
            CardResponse response = new CardResponse();
            response.setStatusCode(0);
            response.setMessage("successful");
            return ResponseEntity.ok().body(response);
        } else {
 
            CardResponse response = new CardResponse();
            response.setStatusCode(1); 
            response.setMessage("failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}