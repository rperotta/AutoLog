package com.rperotta.autolog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreationDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;  // es. email o nickname

    @NotBlank(message = "Password cannot be blank")
    private String password;  // in futuro criptata con BCrypt

    @NotBlank(message = "Role cannot be blank")
    private String role;
}
