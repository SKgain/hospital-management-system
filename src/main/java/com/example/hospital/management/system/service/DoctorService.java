package com.example.hospital.management.system.service;

import com.example.hospital.management.system.DTO.DoctorRequestDTO;
import com.example.hospital.management.system.DTO.DoctorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    DoctorRequestDTO addDoctor(DoctorRequestDTO doctorRequestDTO);

    List<DoctorResponseDTO> getAllDoctor();

    DoctorResponseDTO getDoctorByPhoneNumber(String phoneNumber);

    DoctorRequestDTO updateDoctor(int id, DoctorRequestDTO doctorRequestDTO);

    void deleteDoctor(int id);
}
