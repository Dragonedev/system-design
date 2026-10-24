package repository;

import entities.Hospital;
import entities.Patient;

public class PatientRepository {

	private final Hospital hospital;

	public PatientRepository(Hospital hospital) {
		this.hospital = hospital;
	}

	public Patient findPatientByCpf(String cpf) {
		for (Patient p : hospital.getPatients()) {
			if (p.getCpf().equals(cpf)) {
				return p;
			}
		}
		return null;
	}
}