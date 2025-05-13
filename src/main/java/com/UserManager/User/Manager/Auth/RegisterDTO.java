package com.UserManager.User.Manager.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
}
