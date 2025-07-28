package com.example.hospital.management.system.controller;

import com.example.hospital.management.system.DTO.MedicalRecordRequestDTO;
import com.example.hospital.management.system.DTO.MedicalRecordResponseDTO;
import com.example.hospital.management.system.service.MedicalRecordService;
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
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @PostMapping("/medical-record/add")
    public MedicalRecordRequestDTO addMedicalRecord(
            @Valid @RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO
    ){
        medicalRecordService.addMedicalRecord(medicalRecordRequestDTO);
        logger.info("Adding medical record");
        return medicalRecordRequestDTO;
    }

    @GetMapping("/medical-record/all")
    public List<MedicalRecordResponseDTO> getAllMedicalRecord(){
        logger.info("Getting all medical record");
       return medicalRecordService.getAllMedicalRecord();
    }

    @GetMapping("/medical-record/filter/{phoneNumber}")
    public List<MedicalRecordResponseDTO> getdMedicalRecordByPhone(
            @PathVariable String phoneNumber
    ){
        logger.info("getting medical record by phone number");
       return medicalRecordService.getMedicalRecordByPhone(phoneNumber);
    }
    @PutMapping("/medical-record/update/{id}")
    public MedicalRecordRequestDTO updateMedicalRecord(
            @PathVariable int id,
            @Valid @RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO
    ){
        logger.info("updating medical record");
        medicalRecordService.updateMedicalRecord(id, medicalRecordRequestDTO);
        return medicalRecordRequestDTO;
    }
    @DeleteMapping("/medical-record/delete/{id}")
    public String deleteMedicalRecord(
            @PathVariable int id
    ){
        logger.info("deleting medical record");
        medicalRecordService.deleteMedicalRecord(id);
        return "Deleted medical record";
    }
}
