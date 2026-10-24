package controller;

import entities.Patient;
import entities.enums.SeverityLevel;
import service.PatientService;

public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	public void createPatient(Patient patient) {
		patientService.createPatient(patient);
	}

	public void evaluatePatient(String cpf, SeverityLevel severityLevel) {
		patientService.evaluatePatient(cpf, severityLevel);
		;
	}

	public void findAll() {
		patientService.findAll();
	}
}
