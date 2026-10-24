package controller;

import entities.MedicalRecord;
import service.MedicalRecordService;

public class MedicalRecordController {

	private final MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}

	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordService.createMedicalRecord(medicalRecord);
	}

	public void findAll() {
		medicalRecordService.findAll();
	}
}
