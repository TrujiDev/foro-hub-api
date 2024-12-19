package com.trujidev.forohub.foro_hub_api.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.trujidev.forohub.foro_hub_api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.secret}")
  private String apiSecret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
          .withIssuer("foro-hub-api")
          .withSubject(user.getEmail())
          .withClaim("id", user.getId())
          .withExpiresAt(getExpirationTime())
          .sign(algorithm);
    } catch (Exception e) {
      throw new RuntimeException("Error generating token");
    }
  }

  public String getSubject(String token) {
    DecodedJWT verifier = null;

    if (token == null) {
      throw new RuntimeException("Token is required");
    }

    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      DecodedJWT decodedJWT = JWT.require(algorithm)
          .withIssuer("foro-hub-api")
          .build()
          .verify(token.replace("Bearer ", "").trim());
      verifier.getSubject();
    } catch (Exception e) {
      throw new RuntimeException("Invalid token");
    }
    return verifier.getSubject();
  }

  private Instant getExpirationTime() {
    return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
  }

}
