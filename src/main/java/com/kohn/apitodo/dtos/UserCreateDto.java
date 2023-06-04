package com.kohn.apitodo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last name is mandatory") String lastName,
        @Email @NotBlank(message = "Email is mandatory") String email,
        @NotBlank(message = "Password is mandatory") String password) {
}
