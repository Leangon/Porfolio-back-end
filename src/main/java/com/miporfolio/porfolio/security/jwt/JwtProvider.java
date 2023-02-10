package com.miporfolio.porfolio.security.jwt;

import com.miporfolio.porfolio.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.*;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



//Genera el token, metodo de validación y expiración
@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private int expiration;
    
    // Acá se genera el token
    public String genereteToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException exception){
            logger.error("token mal formado");
        }catch (UnsupportedJwtException exception){
            logger.error("token no soportado");
        }catch (ExpiredJwtException exception){
            logger.error("token expirado");
        }catch (IllegalArgumentException exception){
            logger.error("token vacio");
        }catch (SignatureException exception){
            logger.error("Fallo con la firma");
        }
        return false;
    }
}
