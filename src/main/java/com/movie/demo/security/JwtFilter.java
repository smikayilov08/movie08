package com.movie.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String name = null;
        String jwt = null;
        final String header = httpServletRequest.getHeader("Authorization");
        if (header!=null && header.startsWith("Bearer ")){
           jwt=header.substring(7);
           name=jwtUtil.extractUserName(jwt);
        }
        if (name!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails user=service.loadUserByUsername(name);
            if (jwtUtil.validateToken(jwt,user)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        user,null,user.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            };
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
