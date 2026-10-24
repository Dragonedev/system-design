package repository;

import entities.Doctor;
import entities.Hospital;

public class DoctorRepository {

	private final Hospital hospital;

	public DoctorRepository(Hospital hospital) {
		this.hospital = hospital;
	}

	public void findAll() {
		for (Doctor d : hospital.getDoctors()) {
			System.out.println(d);
		}
	}

	public Doctor findDoctorByDoctorCode(String doctorCode) {
		for (Doctor d : hospital.getDoctors()) {
			if (d.getDoctorCode().equals(doctorCode)) {
				return d;
			}
		}
		return null;
	}
}