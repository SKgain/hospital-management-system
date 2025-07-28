package com.example.hospital.management.system.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorResponseDTO {
    private int id;
    private String name;
    private String qualification;
    private int salary;
    private String hospitalName;
    private String phoneNumber;
}
