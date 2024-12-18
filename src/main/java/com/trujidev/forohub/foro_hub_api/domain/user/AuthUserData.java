package com.trujidev.forohub.foro_hub_api.domain.user;

public record AuthUserData(
    String email,
    String password
) {

  public AuthUserData (User user) {
    this(user.getEmail(), user.getPassword());
  }

}
