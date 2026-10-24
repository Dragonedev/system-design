package service;

import entities.Doctor;
import entities.Hospital;
import entities.MedicalRecord;
import entities.Patient;
import exception.InvalidMedicalRecordException;
import repository.DoctorRepository;
import repository.MedicalRecordRepository;
import repository.PatientRepository;

public class MedicalRecordService {

	private final Hospital hospital;
	private final MedicalRecordRepository medicalRecordRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;

	public MedicalRecordService(Hospital hospital, MedicalRecordRepository medicalRecordRepository,
			DoctorRepository doctorRepository, PatientRepository patientRepository) {
		this.hospital = hospital;
		this.medicalRecordRepository = medicalRecordRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}

	public void createMedicalRecord(MedicalRecord medicalRecord, String doctorCode, String cpf) {
		Doctor doctor = doctorRepository.findDoctorByDoctorCode(doctorCode);
		if (doctor == null) {
			throw new InvalidMedicalRecordException("doctor cannot be null");
		}

		Patient patient = patientRepository.findPatientByCpf(cpf);
		if (patient == null) {
			throw new InvalidMedicalRecordException("doctor cannot be null");
		}

		if (medicalRecord.getProtocolCode() == null || medicalRecord.getProtocolCode().isBlank()) {
			throw new InvalidMedicalRecordException("protocol code cannot be null or blank");
		}

		if (medicalRecord.getDescription() == null || medicalRecord.getDescription().isBlank()) {
			throw new InvalidMedicalRecordException("description cannot be null or blank");
		}

		MedicalRecord savedMedicalRecord = new MedicalRecord(medicalRecord.getDoctor(), medicalRecord.getPatient(),
				medicalRecord.getProtocolCode(), medicalRecord.getDescription());

		hospital.addMedicalRecord(savedMedicalRecord);
	}

	public void findAll() {
		medicalRecordRepository.findAll();
	}

}