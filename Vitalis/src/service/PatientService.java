package service;

import entities.Hospital;
import entities.Patient;
import entities.enums.SeverityLevel;
import exception.InvalidPatientException;
import repository.PatientRepository;

public class PatientService {

	private final Hospital hospital;
	private final PatientRepository patientRepository;

	public PatientService(Hospital hospital, PatientRepository patientRepository) {
		this.hospital = hospital;
		this.patientRepository = patientRepository;
	}

	public void createPatient(Patient patient) {
		if (patient == null) {
			throw new InvalidPatientException("Patient cannot be null");
		}

		if (patient.getAge() < 0) {
			throw new InvalidPatientException("Age cannot be negative");
		}

		if (patient.getName() == null || patient.getName().isBlank()) {
			throw new InvalidPatientException("Name cannot be blank");
		}

		if (patient.getCpf() == null || patient.getCpf().isBlank()) {
			throw new InvalidPatientException("CPF cannot be blank");
		}

		if (patient.getProtocol() == null || patient.getProtocol().isBlank()) {
			throw new InvalidPatientException("Protocol cannot be blank");
		}

		if (patient.getSeverityLevel() != null) {
			throw new InvalidPatientException("Severity level is already set");
		}

		Patient savedPatient = new Patient(patient.getName(), patient.getAge(), patient.getCpf(),
				patient.getProtocol());

		hospital.addPatient(savedPatient);
	}

	public void evaluatePatient(String cpf, SeverityLevel severityLevel) {
		Patient patient = patientRepository.findPatientByCpf(cpf);

		if (patient == null) {
			throw new InvalidPatientException("Patient not found");
		}

		if (severityLevel == null) {
			throw new InvalidPatientException("Severity level cannot be null");
		}

		patient.setSeverityLevel(severityLevel);
	}
}