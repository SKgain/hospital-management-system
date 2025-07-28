package com.example.hospital.management.system.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class DoctorRequestDTO {
    @NotBlank(message = "Must enter a name")
    private String name;
    @NotBlank(message = "Must enter a qualification")
    private String qualification;
    @NotBlank(message = "Must enter phone number")
    @Size(min = 11, max = 11, message = "Phone number must be exactly 11 digits")
    @Pattern(regexp = "^\\d{11}$", message = "Phone number must contain only digits")
    private String phoneNumber;
    @NotNull(message = "Hospital ID must not be null")
    @Positive(message = "Hospital ID must be a positive number")
    private int hospitalId;
    @NotNull
    @Positive
    private int salary;
}
