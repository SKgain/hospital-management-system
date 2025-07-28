package com.example.hospital.management.system.service.impl;

import com.example.hospital.management.system.DTO.PatientRequestDTO;
import com.example.hospital.management.system.DTO.PatientResponseDTO;
import com.example.hospital.management.system.entity.Hospital;
import com.example.hospital.management.system.entity.Patient;
import com.example.hospital.management.system.exception.DuplicatResourceException;
import com.example.hospital.management.system.exception.ResourceNotFoundException;
import com.example.hospital.management.system.repository.HospitalRepository;
import com.example.hospital.management.system.repository.MedicalRecordRepository;
import com.example.hospital.management.system.repository.PatientRepository;
import com.example.hospital.management.system.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public PatientRequestDTO admitPatient(
            @Valid PatientRequestDTO patientRequestDTO
    ){
       Optional<Patient> existPatient = patientRepository
                .findByPhoneNumber(patientRequestDTO.getPhoneNumber());
        if(existPatient.isPresent()){
            throw new DuplicatResourceException("Patient along with phone number: " + patientRequestDTO.getPhoneNumber() +" is already exist");
        }
        else{
            Patient patient = new Patient();
            patient.setName(patientRequestDTO.getName());
            patient.setAddress(patientRequestDTO.getAddress());
            patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());

            Hospital hospital = hospitalRepository
                    .findById(patientRequestDTO.getHospitalId())
                    .orElseThrow(()-> new IllegalArgumentException("Hospital not found"));
            patient.setHospital(hospital);
          patientRepository.save(patient);
          return patientRequestDTO;
        }
    }

    @Override
    public List<PatientResponseDTO> getAllPatient(){
       List<Patient> patients = patientRepository
                .findAll();
       List<PatientResponseDTO> patientResponseDTOList = new ArrayList<>();
       for (Patient patient : patients) {
           PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
           patientResponseDTO.setId(patient.getId());
           patientResponseDTO.setName(patient.getName());
           patientResponseDTO.setAddress(patient.getAddress());
           patientResponseDTO.setPhoneNumber(patient.getPhoneNumber());
           patientResponseDTO.setHospitalName(patient.getHospital().getName());
           patientResponseDTOList.add(patientResponseDTO);
       }
       return patientResponseDTOList;
    }
    @Override
    public PatientResponseDTO getPatientByPhone(String phoneNumber){
        Patient patient = patientRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(()->new ResourceNotFoundException("Patient not found along with phone number: " + phoneNumber));
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setPhoneNumber(patient.getPhoneNumber());
        patientResponseDTO.setHospitalName(patient.getHospital().getName());

        return patientResponseDTO;
    }

    @Override
    public PatientRequestDTO updatePatient(
            int id,
            @Valid PatientRequestDTO patientRequestDTO
    ){
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient not found along with id: " + id));
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());

        patientRepository.save(patient);
        return patientRequestDTO;
    }
    @Override
    public  String deletePatient(int id){
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient not found along with id: " + id));
        patientRepository.delete(patient);
        return "Patient has been deleted";
    }
}
