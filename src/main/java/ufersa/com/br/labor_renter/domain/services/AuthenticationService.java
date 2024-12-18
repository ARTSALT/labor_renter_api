package ufersa.com.br.labor_renter.domain.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Collections;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class AuthenticationService {
    static final long EXPIRATIONTIME = 3_652_500L * 24 * 60 * 60 * 1000;
    static final String SIGNINGKEY = "0 segredo precisa ser longo para não dará pau";
    static final String PREFIX = "Bearer";
    private static final SecretKey SECRETKEY = Keys.hmacShaKeyFor (SIGNINGKEY.getBytes());

    static public void addToken (HttpServletResponse res, String email) {
        System.out.println("Chegou aqui!");
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATIONTIME);
        String name;
        String JwtToken = Jwts.builder()
                .claim("sub", email)
                .claim("iat", now.getTime())
                .claim("exp", expirationDate.getTime())
                .signWith(SECRETKEY)
                .compact();
        res.addHeader("Authorization", PREFIX + " " + JwtToken);
        res.addHeader("Access-Control-Expose-Headers","Authorization");
    }


    static public Authentication getAuthentication (HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null){
            if (token.startsWith(PREFIX)) token = token.substring(PREFIX.length()).trim();
            else throw new MalformedJwtException("Invalid Autorization header format");
            Claims claims = Jwts.parser()
                    .verifyWith(SECRETKEY)
                    .build()
                    .parseEncryptedClaims(token)
                    .getPayload();
            String email = claims.get("sub",String.class);
            if (email != null){
                return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
            }
        }
        return null;
    }
}
