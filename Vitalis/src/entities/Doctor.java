package entities;

import entities.enums.MedicalTrainingLevel;

public class Doctor {

	private String name;
	private String doctorCode;
	private String specialty;
	private MedicalTrainingLevel medicalTrainingLevel;

	public Doctor(String name, String doctorCode, String specialty, MedicalTrainingLevel medicalTrainingLevel) {
		this.name = name;
		this.doctorCode = doctorCode;
		this.specialty = specialty;
		this.medicalTrainingLevel = medicalTrainingLevel;
	}

	public String getName() {
		return name;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public String getSpecialty() {
		return specialty;
	}

	public MedicalTrainingLevel getMedicalTrainingLevel() {
		return medicalTrainingLevel;
	}

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", doctorCode=" + doctorCode + ", specialty=" + specialty
				+ ", medicalTrainingLevel=" + medicalTrainingLevel + "]";
	}

}