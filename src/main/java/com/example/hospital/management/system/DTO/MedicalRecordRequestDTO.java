package com.example.hospital.management.system.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Validated
public class MedicalRecordRequestDTO {
    @NotNull
    private LocalDateTime dateOfExamination;
    @NotBlank(message = "Enter patient problem")
    private String problem;
    @NotBlank(message = "Must enter phone number")
    @Size(min = 11, max = 11, message = "Phone number must be exactly 11 digits")
    @Pattern(regexp = "^\\d{11}$", message = "Phone number must contain only digits")
    private String patientPhone;
}
