package org.launchCode.procrastiNOT.models.dto;

import jakarta.validation.constraints.*;


public class RegisterFormDTO {

    @NotNull
    @NotBlank(message = "Username is required!")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @Email(message = "Please enter a valid email")
    @NotNull
    @NotBlank(message = "Email is required!")
    private String email;

    @NotNull
    @NotBlank(message = "Password is required!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,30}$", message = "Password requires at least one uppercase letter, one lowercase, and a digit (6-30 characters).")
    private String password;

    @NotNull
    @NotBlank(message = "Verify password is required!")
    private String verifyPassword;

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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}


