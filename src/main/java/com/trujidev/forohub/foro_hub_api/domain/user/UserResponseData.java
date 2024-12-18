package com.trujidev.forohub.foro_hub_api.domain.user;

public record UserResponseData(
    Long id,
    String name,
    String email,
    String profile
) {
}
