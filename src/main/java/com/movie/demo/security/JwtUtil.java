package com.movie.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    private String createToken(Map<String,Object>claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+100*60*60*60))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public <T>T extractClaims(String token,Function<Claims,T>claimsResolver){
        Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public Date extractDate(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    private Boolean expriedToken(String token){
        return extractDate(token).before(new Date());
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        return (!expriedToken(token))&& (extractUserName(token).equals(userDetails.getUsername()));
    }
}
