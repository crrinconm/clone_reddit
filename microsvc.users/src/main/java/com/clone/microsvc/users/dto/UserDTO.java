package com.clone.microsvc.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {

    private Long id;

    @NotNull
    @Email(message="this field must be a valid email address")
    private String email;

    @NotNull
    @NotBlank(message = "this field can´t be empty")
    private String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,15}$", message=" this password must be min 6 and max 15 length containing {} atleast () 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    private String photo;
}
