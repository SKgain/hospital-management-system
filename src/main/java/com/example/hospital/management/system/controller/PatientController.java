package com.example.hospital.management.system.controller;

import com.example.hospital.management.system.DTO.PatientRequestDTO;
import com.example.hospital.management.system.DTO.PatientResponseDTO;
import com.example.hospital.management.system.service.PatientService;
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
public class PatientController {
    private final PatientService patientService;
    Logger logger = LoggerFactory.getLogger(PatientController.class);

    @PostMapping("/patient/admit")
    public PatientRequestDTO admitPatient(
           @Valid @RequestBody PatientRequestDTO  patientRequestDTO
    ) {
        patientService.admitPatient(patientRequestDTO);
        logger.info("admitting patient");
        return patientRequestDTO;
    }
    @GetMapping("/patient/all")
    public List<PatientResponseDTO> getAllPatients() {
        logger.info("getting All Patients");
        return patientService.getAllPatient();

    }
    @GetMapping("/patient/filter/{phoneNumber}")
    public PatientResponseDTO getPatientByPhone(
            @PathVariable String phoneNumber
    ){
        logger.info("getting Patient By Phone");
        return patientService.getPatientByPhone(phoneNumber);
    }
    @PutMapping("/patient/update/{id}")
    public PatientRequestDTO updatePatient(
            @Valid @RequestBody PatientRequestDTO  patientRequestDTO,
            @PathVariable int id
    ){
        patientService.updatePatient(id, patientRequestDTO);
        logger.info("updating patient");
        return patientRequestDTO;
    }
    @DeleteMapping("/patient/delete/{id}")
    public String deletePatient(
            @PathVariable int id
    ){
        patientService.deletePatient(id);
        logger.info("deleting patient");
        return "Patient Deleted";
    }
}
