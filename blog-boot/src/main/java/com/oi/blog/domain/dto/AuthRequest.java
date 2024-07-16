package com.oi.blog.domain.dto;

import lombok.Data;

/**
 * @author panpan
 */
@Data
public class AuthRequest {
    private String username;
    private String password;
}