package com.trujidev.forohub.foro_hub_api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserData(
    @NotBlank String name,
    @NotBlank @Email String email,
    @NotNull Profile profile,
    @NotNull @NotBlank String password
) {
}
