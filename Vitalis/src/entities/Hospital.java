package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {

	private final List<Doctor> doctors;
	private final List<Patient> patients;
	private final List<Room> rooms;
	private final List<Sector> sectors;
	private final List<MedicalRecord> medicalRecords;

	public Hospital() {
		this.doctors = new ArrayList<>();
		this.patients = new ArrayList<>();
		this.rooms = new ArrayList<>();
		this.sectors = new ArrayList<>();
		this.medicalRecords = new ArrayList<>();
	}

	public List<Doctor> getDoctors() {
		return Collections.unmodifiableList(doctors);
	}

	public List<Patient> getPatients() {
		return Collections.unmodifiableList(patients);
	}

	public List<Room> getRooms() {
		return Collections.unmodifiableList(rooms);
	}

	public List<Sector> getSectors() {
		return Collections.unmodifiableList(sectors);
	}

	public List<MedicalRecord> getMedicalRecords() {
		return Collections.unmodifiableList(medicalRecords);
	}

	public void addDoctor(Doctor doctor) {
		doctors.add(doctor);
	}

	public void addPatient(Patient patient) {
		patients.add(patient);
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public void addSector(Sector sector) {
		sectors.add(sector);
	}

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
	}
	
	
}