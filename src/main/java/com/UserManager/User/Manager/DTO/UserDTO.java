package com.UserManager.User.Manager.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String password;
}
