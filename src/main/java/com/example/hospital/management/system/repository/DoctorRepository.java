package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);
}
