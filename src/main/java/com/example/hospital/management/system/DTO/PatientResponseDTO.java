package com.example.hospital.management.system.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientResponseDTO {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String hospitalName;
}
