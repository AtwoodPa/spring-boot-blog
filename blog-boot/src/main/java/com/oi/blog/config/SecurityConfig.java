package com.oi.blog.config;

import com.oi.blog.filter.JwtAuthenticationTokenFilter;
import com.oi.blog.service.CustomUserDetailsService;
import com.oi.blog.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * spring security配置
 *
 * @author supanpan
 * @date 2024/07/16
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)// 开启网络安全注解
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * 认证处理类 - ApplicationConfig -> authenticationProvider()
     */
    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    /**
     * 配置白名单地址
     */
    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用HTTP响应标头
                .headers((headersCustomizer) -> {
                    headersCustomizer.cacheControl(cache -> cache.disable()).frameOptions(options -> options.sameOrigin());
                })
                // 禁用session
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                    // 配置白名单URL允许匿名访问
                    authorizeRequests.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated();
                })
                // 注册身份验证提供程序
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
