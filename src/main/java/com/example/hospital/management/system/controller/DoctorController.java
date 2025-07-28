package com.example.hospital.management.system.controller;

import com.example.hospital.management.system.DTO.DoctorRequestDTO;
import com.example.hospital.management.system.DTO.DoctorResponseDTO;
import com.example.hospital.management.system.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gain-medical")
@CrossOrigin
public class DoctorController {
    private final DoctorService doctorService;

    Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @PostMapping("/doctor/add")
    public DoctorRequestDTO addDoctor(
          @Valid @RequestBody DoctorRequestDTO doctorRequestDTO
    ) {
        logger.info("Doctor Add Request");
        doctorService.addDoctor(doctorRequestDTO);
        return doctorRequestDTO;
    }

    @GetMapping("/doctor/all")
    public List<DoctorResponseDTO> getAllDoctors() {
        logger.info("Doctor Get All Request");
        return doctorService.getAllDoctor();
    }

    @GetMapping("/doctor/filter/{phoneNumber}")
    public DoctorResponseDTO getDoctorsByPhoneNumber(
            @PathVariable String phoneNumber
    ) {
        logger.info("Doctor Filter Request");
        return doctorService.getDoctorByPhoneNumber(phoneNumber);
    }

    @PutMapping("/doctor/update/{id}")
    public DoctorRequestDTO updateDoctor(
            @PathVariable int id,
            @Valid @RequestBody DoctorRequestDTO doctorRequestDTO
    ){
        logger.info("Doctor Update Request");
        doctorService.updateDoctor(id, doctorRequestDTO);
        return doctorRequestDTO;
    }

    @DeleteMapping("/doctor/delete/{id}")
    public String deleteDoctor(
            @PathVariable int id
    ){
        logger.info("Doctor Delete Request");
        doctorService.deleteDoctor(id);
        return "Successfully deleted Doctor";
    }
}
