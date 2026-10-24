package service;

import entities.Doctor;
import entities.Hospital;
import exception.InvalidDoctorException;
import repository.DoctorRepository;

public class DoctorService {

    private final Hospital hospital;
    private final DoctorRepository doctorRepository;

    public DoctorService(Hospital hospital, DoctorRepository doctorRepository) {
        this.hospital = hospital;
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new InvalidDoctorException("Doctor cannot be null");
        }

        if (doctor.getName() == null || doctor.getName().isBlank()) {
            throw new InvalidDoctorException("Name cannot be blank");
        }

        if (doctor.getDoctorCode() == null || doctor.getDoctorCode().isBlank()) {
            throw new InvalidDoctorException("Doctor code cannot be blank");
        }

        if (doctor.getSpecialty() == null || doctor.getSpecialty().isBlank()) {
            throw new InvalidDoctorException("Specialty cannot be blank");
        }

        Doctor savedDoctor = new Doctor(
            doctor.getName(),
            doctor.getDoctorCode(),
            doctor.getSpecialty(),
            doctor.getMedicalTrainingLevel()
        );

        hospital.addDoctor(savedDoctor);
        return savedDoctor;
    }

    public void findAll() {
        doctorRepository.findAll();
    }
}