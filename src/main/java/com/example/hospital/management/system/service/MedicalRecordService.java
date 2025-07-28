package com.example.hospital.management.system.service;

import com.example.hospital.management.system.DTO.MedicalRecordRequestDTO;
import com.example.hospital.management.system.DTO.MedicalRecordResponseDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalRecordService {
    MedicalRecordRequestDTO addMedicalRecord(@Valid MedicalRecordRequestDTO medicalRecordRequestDTO);

    List<MedicalRecordResponseDTO> getAllMedicalRecord();

    List<MedicalRecordResponseDTO> getMedicalRecordByPhone(String phoneNumber);

    MedicalRecordRequestDTO updateMedicalRecord(int id, @Valid MedicalRecordRequestDTO medicalRecordRequestDTO);

    String deleteMedicalRecord(int id);
}
