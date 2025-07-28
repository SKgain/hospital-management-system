package com.example.hospital.management.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonBackReference(value = "patient-hospital")
    private Hospital hospital;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<MedicalRecord> medicalRecords;
}
