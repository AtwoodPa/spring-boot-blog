package com.oi.blog.service;

import com.oi.blog.common.HttpStatus;
import com.oi.blog.domain.User;
import com.oi.blog.domain.dto.AuthResponse;
import com.oi.blog.mapper.UsersMapper;
import com.oi.blog.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author supanpan
 * @date 2024/07/16
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersMapper usersMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public int register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreated(new Date());
        user.setLastModified(new Date());
        return usersMapper.save(user);
    }

    public AuthResponse login(String username, String password) {
        User user = usersMapper.findByUsername(username);
        if (user == null) {
            new UsernameNotFoundException("用户查找失败： " + username);
        }
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return new AuthResponse(token);
        } else {
            throw new RuntimeException("认证失败");
        }
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public String me() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new RuntimeException("获取用户信息异常：" + HttpStatus.UNAUTHORIZED);
            }
            return  authentication.getName();
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息异常：" + HttpStatus.UNAUTHORIZED);
        }
    }
}
