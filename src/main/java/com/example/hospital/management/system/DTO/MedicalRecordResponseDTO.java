package com.example.hospital.management.system.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Validated
public class MedicalRecordResponseDTO {

    private int id;
    private String patientName;
    private String problem;
    private LocalDateTime dateOfExamination;

}
