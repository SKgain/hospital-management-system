package com.example.hospital.management.system.service.impl;

import com.example.hospital.management.system.DTO.HospitalDTO;
import com.example.hospital.management.system.entity.Hospital;
import com.example.hospital.management.system.repository.HospitalRepository;
import com.example.hospital.management.system.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
     public HospitalDTO addHospital(@Valid HospitalDTO hospitalDTO){
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDTO.getName());
        hospital.setAddress(hospitalDTO.getAddress());
        hospital.setPhoneNumber(hospitalDTO.getPhoneNumber());
        hospitalRepository.save(hospital);
        return hospitalDTO;
    }
    @Override
    public List<HospitalDTO> getAllHospital(){
        List<Hospital>  hospital = hospitalRepository.findAll();
        List<HospitalDTO> hospitalDTOS = new ArrayList<>();
        for(Hospital hospital1:hospital){
            HospitalDTO hospitalDTO = new HospitalDTO();
            hospitalDTO.setName(hospital1.getName());
            hospitalDTO.setAddress(hospital1.getAddress());
            hospitalDTO.setPhoneNumber(hospital1.getPhoneNumber());
            hospitalDTOS.add(hospitalDTO);
        }
        return hospitalDTOS;
    }
}
