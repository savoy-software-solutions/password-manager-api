package com.johnsavoy.password_manager_api.dto;

public class AuthResponse {
    private String token;

    public AuthResponse() {
        this.token = token;
    }


    //Getter & Setters
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
