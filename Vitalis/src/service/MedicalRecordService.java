package service;

import entities.Hospital;
import entities.MedicalRecord;
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

	}

}