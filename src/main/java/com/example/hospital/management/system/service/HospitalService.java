package com.example.hospital.management.system.service;

import com.example.hospital.management.system.DTO.HospitalDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalService {
    HospitalDTO addHospital(@Valid HospitalDTO hospitalDTO);

    List<HospitalDTO> getAllHospital();
}
