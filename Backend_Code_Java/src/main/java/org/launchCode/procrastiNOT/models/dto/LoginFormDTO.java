package org.launchCode.procrastiNOT.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginFormDTO {
    @NotNull
    @NotBlank(message = "Username is required!")
    @Size(min = 4, max = 20, message = "Invalid username")
    private String username;

    @NotNull
    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 30, message = "Invalid password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,30}$", message = "Invalid password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




