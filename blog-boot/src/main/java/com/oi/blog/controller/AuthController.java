package com.oi.blog.controller;

import com.oi.blog.common.Result;
import com.oi.blog.domain.User;
import com.oi.blog.domain.dto.AuthRequest;
import com.oi.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 认证授权接口
 *
 * @author supanpan
 * @date 2024/07/16
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return Result.ok(authService.register(user) == 1 ? "注册成功" : "注册失败",user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody AuthRequest authRequest) {
        return Result.ok(authService.login(authRequest.getUsername(), authRequest.getPassword()));
    }

    @GetMapping("/me")
    public Result me() {
        return Result.ok(authService.me());
    }
}
