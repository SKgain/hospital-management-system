package com.example.hospital.management.system.entity;

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
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy ="hospital", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "patient-hospital")
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "doctor-hospital")
    private List<Doctor> doctors;
}
