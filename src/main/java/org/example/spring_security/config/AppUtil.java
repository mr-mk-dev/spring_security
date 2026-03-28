package org.example.spring_security.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.spring_security.entity.Users;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AppUtil {
    private final  String JWTSECRETKEY = "manishkumarprjajaapati1231123332123323";

  private SecretKey secretKey(){
      return Keys.hmacShaKeyFor(JWTSECRETKEY.getBytes(StandardCharsets.UTF_8));
  }
  public  String generateToken(Users users){
      return Jwts.builder()
              .subject(users.getUsername() )
              .claim( "userId",users.getId().toString())
              .issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
              .signWith(secretKey())
              .compact();
  }
}
