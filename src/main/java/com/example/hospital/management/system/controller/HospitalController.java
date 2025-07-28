package com.example.hospital.management.system.controller;

import com.example.hospital.management.system.DTO.HospitalDTO;
import com.example.hospital.management.system.service.HospitalService;
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
public class HospitalController {
    private final HospitalService hospitalService;
    Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @PostMapping
    public HospitalDTO addHospital(
            @Valid @RequestBody HospitalDTO hospitalDTO
    ) {
        hospitalService.addHospital(hospitalDTO);
        return hospitalDTO;
    }
    @GetMapping
    public List<HospitalDTO> getAllHospital() {
        return hospitalService.getAllHospital();
    }
}
