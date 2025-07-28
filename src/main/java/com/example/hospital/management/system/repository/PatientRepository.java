package com.example.hospital.management.system.repository;


import com.example.hospital.management.system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {


    Optional<Patient> findByPhoneNumber(String phoneNumber);
}
