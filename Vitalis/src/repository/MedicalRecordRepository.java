package repository;

import entities.Hospital;
import entities.MedicalRecord;

public class MedicalRecordRepository {

	private final Hospital hospital;

	public MedicalRecordRepository(Hospital hospital) {
		this.hospital = hospital;
	}

	public MedicalRecord findDoctorByDoctorCode(String protocolCode) {
		for (MedicalRecord md : hospital.getMedicalRecords()) {
			if (md.getProtocolCode().equals(protocolCode)) {
				return md;
			}
		}
		return null;
	}
}