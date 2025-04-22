package com.java.springboot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "UserName should'nt be a null value ")
    private String name;
    @NotBlank(message = "Email id not be null")
    @Email(message = "Invalid email id")
    private String email;
    @NotNull(message = "mobile number not be null")
    @Pattern(regexp = "^\\d{10}$")
    private String mobile;
    @NotBlank(message = "gender not a null value")
    private String gender;
    @Min(18)
    @Max(60)
    private int age;
    @NotBlank(message = "Nationality not be null")
    private String nationality;
}
