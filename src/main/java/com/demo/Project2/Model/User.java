package com.demo.Project2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "UserID is required")
    @Size(min = 3, message = "UserID have to be 3 character long")
    private String UserID;

    @NotEmpty(message = "username is required")
    @Size(min = 5, message = "username have to be 5 length long")
    private String username;

    @NotEmpty(message = "password is required")
    @Size(min = 6, message = "password have to be 6 length long")
    //must have characters and digits
    @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "password must have characters and digits, must be strong")
    private String password;

    @NotEmpty(message = "email is required")
    @Email(message = "must be valid email")
    private String email;

    @NotEmpty(message = "role is required")
    @Pattern(regexp = "(Admin | Customer)", message = "role must be in (Admin, Customer)")
    private String role;

    @NotNull(message = "balance is required")
    @Positive(message = "balance have to be positive")
    private Integer balance;

}
