package com.oi.blog.domain.dto;

import lombok.Data;

/**
 * @author panpan
 */
@Data
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

}