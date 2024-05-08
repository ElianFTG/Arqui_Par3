package com.ucb.demo.dto;

public class LoginToDto {
    private String token;
    private String timestamp;
 public LoginToDto() {
     
 }
 
 public String getToken() {
     return token;
 }
 public String getTime() {
     return timestamp;
 }
 public void setToken(String token) {
    this.token = token;
 }
 public void setTime(String timestamp) {
    this.timestamp = timestamp;
 }
}
