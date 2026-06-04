package com.spring.resturant.config.filter;

import com.spring.resturant.config.TokenHandler;
import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.dto.Exception.Found;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.service.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final TokenHandler tokenHandler;
    private final CustomerService customerService;

    public AuthFilter(TokenHandler tokenHandler,
                      CustomerService customerService) {
        this.tokenHandler = tokenHandler;
        this.customerService = customerService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return path.startsWith("/auth")
                || path.startsWith("/v3")
                || path.startsWith("/swagger-ui");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // ✅ 1. سيب preflight requests تعدي
        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 2. خُد التوكن
        String header = request.getHeader("Authorization");

        // لو مفيش توكن → كمل عادي (ممكن endpoint public)
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        // ❌ توكن غير صالح
        if (!tokenHandler.isValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 👤 استخراج username
        String username = tokenHandler.extractUsername(token);

        CustomerDto user = null;
        try {
            user = customerService.getEmployeeByUserName(username);
        } catch (Found e) {
            throw new RuntimeException(e);
        } catch (NotFound e) {
            throw new RuntimeException(e);
        }

        // 🔐 roles → authorities
        List<GrantedAuthority> roles = user.getRoleDtos()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(java.util.stream.Collectors.toList());

        // 🔐 إنشاء Authentication
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, roles);

        SecurityContextHolder.getContext().setAuthentication(auth);

        // ➜ كمل request
        filterChain.doFilter(request, response);
    }
}