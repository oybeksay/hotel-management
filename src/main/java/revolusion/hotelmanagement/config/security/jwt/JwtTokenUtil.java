package revolusion.hotelmanagement.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final String SECRET_KEY = "V6Xb8XJ3EFY/EqtSKbPpFGqnsi/R2GHr/sQpj2YqixY=";

    public String generateToken(@NonNull String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(signKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key signKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isValid(String token) {
        try{
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }catch (Exception e){
            log.info("Token is not valid: {}", e.getMessage());
        }
        return false;
    }

    public String getUsername(@NonNull String token) {
        Claims body = getClaims(token);
        return body.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}