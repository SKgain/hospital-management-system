package com.example.hospital.management.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String qualification;
    private String phoneNumber;
    private int salary;

    @ManyToOne
    @JoinColumn(name="hospital_id")
    @JsonBackReference(value = "doctor-hospital")
    private Hospital hospital;
}
