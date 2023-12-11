package org.launchCode.procrastiNOT.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginFormDTO {
    @NotNull(message = "username must be entered")
    @NotBlank(message = "username must be entered")
    @Size(min = 3, max = 20, message = "Invalid username! Username must be between 3 and 20 characters.")
    private String username;

    @NotNull(message = "username must be entered")
    @NotBlank(message = "username must be entered")
    @Size(min = 6, max = 30, message = "Invalid password! Password must be between 6 and 30 characters.")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername() {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


