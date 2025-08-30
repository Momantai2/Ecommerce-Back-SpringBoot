package com.ecommerce.ecommerce.dto;


public class JwtResponse {
    private String token;
    private Long idRol;

    public JwtResponse(String token, Long rol) {
        this.token = token;
        this.idRol = rol;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getRol() {
        return idRol;
    }

    public void setRol(Long rol) {
        this.idRol = rol;
    }
}
