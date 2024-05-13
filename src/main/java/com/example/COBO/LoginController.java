package com.example.COBO;


import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;

import io.sentry.Sentry;
import jakarta.validation.Valid;

@RestController
public class LoginController implements ILoginApi{

    @PostMapping("/login")
     public String index() {
      

        try {
            throw new Exception("This is a test.");
          } catch (Exception e) {
            Sentry.captureException(e);
          }
        return "Greetings from Spring boot";
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String token = generateJWTToken(); 
        LoginResponse response = new LoginResponse();
        try {
            JsonNode schemaNode = objectMapper.readTree(getClass().getResourceAsStream("schemas/login.json"));
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaNode);
    
           Set<ValidationMessage> errors = schema.validate(schemaNode); 
    
                String errorsCombined = "";
                for( ValidationMessage error: errors) {
                    errorsCombined += error.toString() +  "\n";
                }
    
                if(errors.size() > 0) {
                    return ResponseEntity.badRequest().body(response);
                }
                return ResponseEntity.ok(response);
    
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResponseEntity.ok(response);
            }
    }

    private String generateJWTToken() {
        return "token_generado";
    }

  }