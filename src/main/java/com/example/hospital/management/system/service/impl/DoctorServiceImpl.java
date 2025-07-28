package com.example.hospital.management.system.service.impl;

import com.example.hospital.management.system.DTO.DoctorRequestDTO;
import com.example.hospital.management.system.DTO.DoctorResponseDTO;
import com.example.hospital.management.system.entity.Doctor;
import com.example.hospital.management.system.entity.Hospital;
import com.example.hospital.management.system.exception.DuplicatResourceException;
import com.example.hospital.management.system.exception.ResourceNotFoundException;
import com.example.hospital.management.system.repository.DoctorRepository;
import com.example.hospital.management.system.repository.HospitalRepository;
import com.example.hospital.management.system.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    @Override
    public DoctorRequestDTO addDoctor(DoctorRequestDTO doctorRequestDTO){

        Optional<Doctor> existDoctor = doctorRepository
                .findByPhoneNumber( doctorRequestDTO.getPhoneNumber() );
        if(existDoctor.isPresent()){
            throw new DuplicatResourceException("Doctor already exists along with phone number: "+doctorRequestDTO.getPhoneNumber());
        }
        else {
            Doctor doctor = new Doctor();
            doctor.setName(doctorRequestDTO.getName());
            doctor.setQualification(doctorRequestDTO.getQualification());
            doctor.setPhoneNumber(doctorRequestDTO.getPhoneNumber());
            doctor.setSalary(doctorRequestDTO.getSalary());

            Hospital hospital = hospitalRepository.findById(doctorRequestDTO
                            .getHospitalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));
            doctor.setHospital(hospital);

            doctorRepository.save(doctor);

            return doctorRequestDTO;
        }

    }

    @Override
    public List<DoctorResponseDTO> getAllDoctor(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponseDTO> doctorResponseDTOList = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
            doctorResponseDTO.setId(doctor.getId());
            doctorResponseDTO.setName(doctor.getName());
            doctorResponseDTO.setQualification(doctor.getQualification());
            doctorResponseDTO.setPhoneNumber(doctor.getPhoneNumber());
            doctorResponseDTO.setHospitalName(doctor.getHospital().getName());
            doctorResponseDTOList.add(doctorResponseDTO);
        }
        return doctorResponseDTOList;
    }

    @Override
    public DoctorResponseDTO getDoctorByPhoneNumber(String phoneNumber){
        Doctor doctor = doctorRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found along with phone number: " + phoneNumber));
         DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
         doctorResponseDTO.setId(doctor.getId());
         doctorResponseDTO.setName(doctor.getName());
         doctorResponseDTO.setQualification(doctor.getQualification());
         doctorResponseDTO.setPhoneNumber(doctor.getPhoneNumber());
         doctorResponseDTO.setHospitalName(doctor.getHospital().getName());
         return doctorResponseDTO;
    }

    @Override
    public DoctorRequestDTO updateDoctor(int id, DoctorRequestDTO doctorRequestDTO){
        Doctor doctor = doctorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctor.setName(doctorRequestDTO.getName());
        doctor.setQualification(doctorRequestDTO.getQualification());
        doctor.setPhoneNumber(doctorRequestDTO.getPhoneNumber());
        doctor.setSalary(doctorRequestDTO.getSalary());
        doctorRepository.save(doctor);
        return doctorRequestDTO;

    }
    @Override
    public void deleteDoctor(int id){
        Doctor doctor = doctorRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found"));

        doctorRepository.delete(doctor);

    }

}
