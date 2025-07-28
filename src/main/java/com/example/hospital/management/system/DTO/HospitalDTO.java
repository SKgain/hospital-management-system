package com.example.hospital.management.system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class HospitalDTO {
    @NotBlank(message = "Must enter a name")
    private String name;
    @NotBlank(message = "Must enter a address")
    private String address;
    @NotBlank(message = "Must enter phone number")
    @Size(min = 11, max = 11, message = "Phone number must be exactly 11 digits")
    @Pattern(regexp = "^\\d{11}$", message = "Phone number must contain only digits")
    private String phoneNumber;
}
