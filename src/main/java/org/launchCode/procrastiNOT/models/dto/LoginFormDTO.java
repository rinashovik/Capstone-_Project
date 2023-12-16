package org.launchCode.procrastiNOT.models.dto;

import jakarta.validation.constraints.*;

public class LoginFormDTO {
    @NotNull
    @NotBlank(message = "Username is required!")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters.")
    @Pattern(regexp = "^(?!\\d{3}$)(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+$", message = "Invalid username! Must include at least one letter and one digit.")
    private String username;

    @NotNull
    @NotBlank(message = "Email is required!")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotNull
    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Invalid password! Password must have at least one lowercase letter, one uppercase letter, and one digit.")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


