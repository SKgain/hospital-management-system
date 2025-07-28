package com.example.hospital.management.system.service;

import com.example.hospital.management.system.DTO.PatientRequestDTO;
import com.example.hospital.management.system.DTO.PatientResponseDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    PatientRequestDTO admitPatient(@Valid PatientRequestDTO patientRequestDTO);

    List<PatientResponseDTO> getAllPatient();

    PatientResponseDTO getPatientByPhone(String phoneNumber);

    PatientRequestDTO updatePatient(int id, @Valid PatientRequestDTO patientRequestDTO);

    String deletePatient(int id);
}
