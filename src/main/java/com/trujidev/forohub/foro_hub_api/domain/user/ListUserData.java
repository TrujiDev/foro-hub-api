package com.trujidev.forohub.foro_hub_api.domain.user;

public record ListUserData(
    Long id,
    String name,
    String email,
    String profile
) {

  public ListUserData(User user) {
    this(user.getId(), user.getName(), user.getEmail(), user.getProfile().toString());
  }

}
