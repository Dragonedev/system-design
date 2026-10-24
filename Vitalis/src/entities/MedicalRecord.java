package entities;

public class MedicalRecord {

	private final Doctor doctor;
	private final Patient patient;
	private final String protocolCode;
	private final String description;

	public MedicalRecord(Doctor doctor, Patient patient, String protocolCode, String description) {
		this.doctor = doctor;
		this.patient = patient;
		this.protocolCode = protocolCode;
		this.description = description;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public String getProtocolCode() {
		return protocolCode;
	}

	public String getDescription() {
		return description;
	}

}