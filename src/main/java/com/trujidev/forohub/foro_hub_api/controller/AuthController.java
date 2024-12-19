package com.trujidev.forohub.foro_hub_api.controller;

import com.trujidev.forohub.foro_hub_api.domain.user.AuthUserData;
import com.trujidev.forohub.foro_hub_api.domain.user.User;
import com.trujidev.forohub.foro_hub_api.infrastructure.security.JwtDataToken;
import com.trujidev.forohub.foro_hub_api.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<JwtDataToken> authUser(@RequestBody @Valid AuthUserData authUserData) {
    try {
      Authentication authToken = new UsernamePasswordAuthenticationToken(authUserData.email(), authUserData.password());
      Authentication authenticatedUser = authenticationManager.authenticate(authToken);
      String token = tokenService.generateToken((User) authenticatedUser.getPrincipal());
      return ResponseEntity.ok(new JwtDataToken(token));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(401).body(new JwtDataToken("Invalid credentials"));
    }
  }

}
