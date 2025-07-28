package com.example.hospital.management.system.service.impl;

import com.example.hospital.management.system.DTO.MedicalRecordRequestDTO;
import com.example.hospital.management.system.DTO.MedicalRecordResponseDTO;
import com.example.hospital.management.system.entity.MedicalRecord;
import com.example.hospital.management.system.entity.Patient;
import com.example.hospital.management.system.exception.ResourceNotFoundException;
import com.example.hospital.management.system.repository.MedicalRecordRepository;
import com.example.hospital.management.system.repository.PatientRepository;
import com.example.hospital.management.system.service.MedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@RequiredArgsConstructor
public class MedicalRecordImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    @Override
    public MedicalRecordRequestDTO addMedicalRecord(
            @Valid MedicalRecordRequestDTO medicalRecordRequestDTO
    ){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDateOfExamination(medicalRecordRequestDTO.getDateOfExamination());
        medicalRecord.setProblem(medicalRecordRequestDTO.getProblem());

        Patient patient = patientRepository
                .findByPhoneNumber(medicalRecordRequestDTO.getPatientPhone())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found along with phone number: " + medicalRecordRequestDTO.getPatientPhone()));
        medicalRecord.setPatient(patient);
        medicalRecordRepository.save(medicalRecord);
        return medicalRecordRequestDTO;
    }
    @Override
    public List<MedicalRecordResponseDTO> getAllMedicalRecord(){
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<MedicalRecordResponseDTO> medicalRecordResponseDTOList = new ArrayList<>();

        for (MedicalRecord medicalRecord: medicalRecords)
        {
            MedicalRecordResponseDTO  medicalRecordResponseDTO = new MedicalRecordResponseDTO();
            medicalRecordResponseDTO.setId(medicalRecord.getId());
            medicalRecordResponseDTO.setDateOfExamination(medicalRecord.getDateOfExamination());
            medicalRecordResponseDTO.setProblem(medicalRecord.getProblem());
            medicalRecordResponseDTO.setPatientName(medicalRecord.getPatient().getName());

            medicalRecordResponseDTOList.add(medicalRecordResponseDTO);

        }
        return medicalRecordResponseDTOList;
    }
    @Override
    public List<MedicalRecordResponseDTO> getMedicalRecordByPhone(
            String phoneNumber
    ){
        Patient patient = patientRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Patient not found along with the phone number: " + phoneNumber));
        List <MedicalRecord> medicalRecords = patient.getMedicalRecords();
        List<MedicalRecordResponseDTO> medicalRecordResponseDTOList = new ArrayList<>();
       for( MedicalRecord medicalRecord: medicalRecords){
           MedicalRecordResponseDTO medicalRecordResponseDTO = new MedicalRecordResponseDTO();
           medicalRecordResponseDTO.setId(medicalRecord.getId());
           medicalRecordResponseDTO.setPatientName(medicalRecord.getPatient().getName());
           medicalRecordResponseDTO.setProblem(medicalRecord.getProblem());
           medicalRecordResponseDTO.setDateOfExamination(medicalRecord.getDateOfExamination());

           medicalRecordResponseDTOList.add(medicalRecordResponseDTO);
       }
       return medicalRecordResponseDTOList;
    }
    @Override
    public MedicalRecordRequestDTO updateMedicalRecord(
            int id,
            @Valid MedicalRecordRequestDTO medicalRecordRequestDTO
    ){
        MedicalRecord medicalRecord = medicalRecordRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medical Record Not Found"));
        medicalRecord.setDateOfExamination(medicalRecordRequestDTO.getDateOfExamination());
        medicalRecord.setProblem(medicalRecordRequestDTO.getProblem());

        medicalRecordRepository.save(medicalRecord);
        return medicalRecordRequestDTO;
    }
    @Override
    public String deleteMedicalRecord(int id){
        MedicalRecord medicalRecord = medicalRecordRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Medical Record Not Found"));
        medicalRecordRepository.delete(medicalRecord);
        return "Medical Record Deleted";
    }
}
