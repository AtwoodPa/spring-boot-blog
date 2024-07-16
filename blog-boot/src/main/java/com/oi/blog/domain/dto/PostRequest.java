package com.oi.blog.domain.dto;

import com.oi.blog.domain.User;
import lombok.Data;

/**
 * @author panpan
 */
@Data
public class PostRequest {
    private String title;
    private String content;
    private User user;
}