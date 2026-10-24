package controller;

import entities.Doctor;
import service.DoctorService;

public class DoctorController {

	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public Doctor createDoctor(Doctor doctor) {
		return doctorService.createDoctor(doctor);
	}

	public void findAll() {
		doctorService.findAll();
	}
}
