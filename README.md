# 🏥 Gain Medical — Hospital Management System

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![Spring AOP](https://img.shields.io/badge/AOP-Aspects-blueviolet?style=flat-square)
![JPA](https://img.shields.io/badge/ORM-Spring%20Data%20JPA-yellow?style=flat-square)
![Lombok](https://img.shields.io/badge/Lombok-Enabled-red?style=flat-square)
![Validation](https://img.shields.io/badge/Validation-Jakarta-blue?style=flat-square)

A clean, well-structured **Hospital Management REST API** built with Spring Boot. Manages hospitals, doctors, patients, and medical records with full CRUD operations, request/response DTOs, global exception handling, and AOP-based cross-cutting concerns.

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Entity Relationships](#-entity-relationships)
- [API Reference](#-api-reference)
- [Validation](#-validation)
- [Exception Handling](#-exception-handling)
- [AOP](#-aop)
- [Getting Started](#-getting-started)

---

## 🧩 Overview

Gain Medical is a REST API for managing core hospital operations. It supports registering hospitals, onboarding doctors and patients, and maintaining medical records — all linked through a relational entity model with proper cascade behavior and circular reference protection.

---

## ✨ Features

| Category | Capability |
|---|---|
| 🏥 Hospitals | Register hospitals, list all |
| 👨‍⚕️ Doctors | Add, update, delete, filter by phone number |
| 🧑‍🤝‍🧑 Patients | Admit, update, discharge, filter by phone number |
| 📋 Medical Records | Create, update, delete, filter by patient phone |
| 🛡️ Validation | Jakarta Bean Validation on all request DTOs |
| ⚠️ Exceptions | Global exception handler — 404, 409, 400 responses |
| 🔍 AOP | Logging and authorization aspects across all layers |
| 🔗 Relations | Hospital → Doctors, Hospital → Patients, Patient → Medical Records |

---

## 🛠️ Tech Stack

- **Java 21**, Spring Boot 3.x
- **Spring Data JPA** — ORM with `JpaRepository`
- **Spring AOP** — cross-cutting logging and authorization aspects
- **Jakarta Bean Validation** — `@NotBlank`, `@Pattern`, `@Size`, `@Positive`
- **Lombok** — `@Data`, `@Getter`, `@Setter`, `@RequiredArgsConstructor`, `@NoArgsConstructor`
- **SLF4J** — request-level logging in all controllers
- **Jackson** — `@JsonManagedReference` / `@JsonBackReference` for circular reference handling

---

## 🏗️ Architecture

```
com.example.hospital.management.system/
│
├── DTO/                            # Request & Response DTOs (decoupled from entities)
│   ├── DoctorRequestDTO            # Validated input — name, qualification, phone, hospitalId, salary
│   ├── DoctorResponseDTO           # Output — id, name, qualification, salary, hospitalName, phone
│   ├── HospitalDTO                 # Input/Output — name, address, phone
│   ├── MedicalRecordRequestDTO     # Validated input — dateOfExamination, problem, patientPhone
│   ├── MedicalRecordResponseDTO    # Output — id, patientName, problem, dateOfExamination
│   ├── PatientRequestDTO           # Validated input — name, address, phone, hospitalId
│   └── PatientResponseDTO          # Output — id, name, address, phone, hospitalName
│
├── aspect/
│   ├── AuthorizationAspect         # @Before all service methods — authorization logging
│   └── LoginAspect                 # @Before all controller methods — request logging
│
├── controller/
│   ├── HospitalController          # POST, GET /gain-medical
│   ├── DoctorController            # POST, GET, PUT, DELETE /gain-medical/doctor
│   ├── PatientController           # POST, GET, PUT, DELETE /gain-medical/patient
│   └── MedicalRecordController     # POST, GET, PUT, DELETE /gain-medical/medical-record
│
├── entity/
│   ├── Hospital                    # Root entity — OneToMany Doctors, OneToMany Patients
│   ├── Doctor                      # ManyToOne Hospital
│   ├── Patient                     # ManyToOne Hospital, OneToMany MedicalRecords
│   └── MedicalRecord               # ManyToOne Patient
│
├── exception/
│   ├── ResourceNotFoundException   # 404 — entity not found
│   ├── DuplicatResourceException   # 409 — duplicate entry
│   └── GlobalExceptionHandler      # @RestControllerAdvice — handles all exceptions uniformly
│
├── repository/
│   ├── HospitalRepository          # JpaRepository<Hospital, Integer>
│   ├── DoctorRepository            # + findByPhoneNumber()
│   ├── PatientRepository           # + findByPhoneNumber()
│   └── MedicalRecordRepository     # JpaRepository<MedicalRecord, Integer>
│
└── service/
    ├── HospitalService             # Interface
    ├── DoctorService               # Interface
    ├── PatientService              # Interface
    └── MedicalRecordService        # Interface
```

---

## 🔗 Entity Relationships

```
Hospital
  ├── OneToMany → Doctor    (cascade REMOVE)
  └── OneToMany → Patient   (cascade REMOVE, fetch EAGER)
            └── OneToMany → MedicalRecord  (cascade REMOVE)
```

Circular references between parent and child entities are handled using `@JsonManagedReference` and `@JsonBackReference` — preventing infinite recursion during JSON serialization without needing `@JsonIgnore`.

---

## 📡 API Reference

All endpoints are prefixed with `/gain-medical`.

### 🏥 Hospital
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/gain-medical` | Register a new hospital |
| GET | `/gain-medical` | Get all hospitals |

### 👨‍⚕️ Doctor
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/gain-medical/doctor/add` | Add a new doctor |
| GET | `/gain-medical/doctor/all` | Get all doctors |
| GET | `/gain-medical/doctor/filter/{phoneNumber}` | Find doctor by phone |
| PUT | `/gain-medical/doctor/update/{id}` | Update doctor details |
| DELETE | `/gain-medical/doctor/delete/{id}` | Delete a doctor |

### 🧑‍🤝‍🧑 Patient
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/gain-medical/patient/admit` | Admit a new patient |
| GET | `/gain-medical/patient/all` | Get all patients |
| GET | `/gain-medical/patient/filter/{phoneNumber}` | Find patient by phone |
| PUT | `/gain-medical/patient/update/{id}` | Update patient details |
| DELETE | `/gain-medical/patient/delete/{id}` | Discharge/delete a patient |

### 📋 Medical Record
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/gain-medical/medical-record/add` | Create a medical record |
| GET | `/gain-medical/medical-record/all` | Get all records |
| GET | `/gain-medical/medical-record/filter/{phoneNumber}` | Get records by patient phone |
| PUT | `/gain-medical/medical-record/update/{id}` | Update a record |
| DELETE | `/gain-medical/medical-record/delete/{id}` | Delete a record |

---

## ✅ Validation

All request DTOs use **Jakarta Bean Validation** enforced via `@Valid` on controller parameters.

| Field | Rules |
|---|---|
| `name`, `address`, `problem` | `@NotBlank` |
| `phoneNumber` | `@NotBlank` + `@Size(min=11, max=11)` + `@Pattern(^\\d{11}$)` |
| `hospitalId`, `salary` | `@NotNull` + `@Positive` |
| `dateOfExamination` | `@NotNull` |

Validation errors return structured `400 Bad Request` responses with per-field error messages.

---

## ⚠️ Exception Handling

`GlobalExceptionHandler` (`@RestControllerAdvice`) handles all exceptions uniformly:

| Exception | HTTP Status | Trigger |
|---|---|---|
| `ResourceNotFoundException` | `404 Not Found` | Entity not found by ID or phone |
| `DuplicatResourceException` | `409 Conflict` | Duplicate phone number or entry |
| `MethodArgumentNotValidException` | `400 Bad Request` | `@Valid` on `@RequestBody` fails |
| `ConstraintViolationException` | `400 Bad Request` | `@Validated` on path/query params fails |

Validation errors return a field-name to error-message map:
```json
{
  "phoneNumber": "Phone number must be exactly 11 digits",
  "name": "Must enter a name"
}
```

---

## 🔍 AOP

Two aspects handle cross-cutting concerns declaratively, keeping controllers and services clean:

**`LoginAspect`** — fires `@Before` every controller method call:
```
Logging aspect called
```

**`AuthorizationAspect`** — fires `@Before` every service method call:
```
Authorization aspect called
```

This separates observability concerns from business logic entirely — no logging code inside controllers or services.

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+
- MySQL / PostgreSQL (configure in `application.properties`)

### Run

```bash
git clone https://github.com/yourusername/hospital-management-system.git
cd hospital-management-system
mvn spring-boot:run
```

### Sample Request — Admit Patient
```bash
curl -X POST http://localhost:8080/gain-medical/patient/admit \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Rahim Uddin",
    "address": "Dhaka",
    "phoneNumber": "01711223344",
    "hospitalId": 1
  }'
```

---

## 👨‍💻 Author

Built by **Saikat Kumar Gain** — a backend-focused Java developer passionate about clean architecture, scalable APIs, and developer-friendly codebases.

> 📬 Open to full-time backend / Java / Spring Boot roles.
