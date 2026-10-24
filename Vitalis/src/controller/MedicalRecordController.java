package controller;

import entities.MedicalRecord;
import service.MedicalRecordService;

public class MedicalRecordController {

	private final MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}

	public void createMedicalRecord(MedicalRecord medicalRecord, String doctorCode, String cpf) {
		medicalRecordService.createMedicalRecord(medicalRecord, doctorCode, cpf);
	}

	public void findAll() {
		medicalRecordService.findAll();
	}
}
