package application;

import controller.DoctorController;
import controller.MedicalRecordController;
import controller.PatientController;
import entities.Doctor;
import entities.Hospital;
import entities.MedicalRecord;
import entities.Patient;
import entities.enums.MedicalTrainingLevel;
import repository.DoctorRepository;
import repository.MedicalRecordRepository;
import repository.PatientRepository;
import service.DoctorService;
import service.MedicalRecordService;
import service.PatientService;

public class VitalisMain {

	public static void main(String[] args) {

		Hospital hospital = new Hospital();
		DoctorRepository doctorRepository = new DoctorRepository(hospital);
		DoctorService doctorService = new DoctorService(hospital, doctorRepository);
		DoctorController doctorController = new DoctorController(doctorService);

		Doctor doctor1 = doctorController
				.createDoctor(new Doctor("Dr. House", "D-021", "Cardiology", MedicalTrainingLevel.SPECIALIST));
		Doctor doctor2 = doctorController
				.createDoctor(new Doctor("Dr. Gregory", "D-022", "Neurology", MedicalTrainingLevel.SPECIALIST));
		Doctor doctor3 = doctorController
				.createDoctor(new Doctor("Dr. Arthur Black", "D-023", "Pediatrics", MedicalTrainingLevel.SPECIALIST));
		Doctor doctor4 = doctorController
				.createDoctor(new Doctor("Dr. Victor Moreau", "D-024", "Orthopedics", MedicalTrainingLevel.SPECIALIST));

		PatientRepository patientRepository = new PatientRepository(hospital);
		PatientService patientService = new PatientService(hospital, patientRepository);
		PatientController patientController = new PatientController(patientService);

		Patient patient1 = patientController.createPatient(new Patient("John Wick", 35, "12345678901", "P-021"));
		Patient patient2 = patientController.createPatient(new Patient("Sarah Connor", 42, "23456789012", "P-022"));
		Patient patient3 = patientController.createPatient(new Patient("Bruce Wayne", 30, "34567890123", "P-023"));
		Patient patient4 = patientController.createPatient(new Patient("Tony Stark", 48, "45678901234", "P-024"));

		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository(hospital);
		MedicalRecordService medicalRecordService = new MedicalRecordService(hospital, medicalRecordRepository,
				doctorRepository, patientRepository);
		MedicalRecordController medicalRecordController = new MedicalRecordController(medicalRecordService);

		medicalRecordController.createMedicalRecord(new MedicalRecord(doctor1, patient2, "PC-01",
				"Patient diagnosed with a cardiac condition. Follow-up required."));

		medicalRecordController.createMedicalRecord(new MedicalRecord(doctor2, patient1, "PC-02",
				"Patient presents recurring headaches. Neurological evaluation recommended."));

		medicalRecordController.createMedicalRecord(
				new MedicalRecord(doctor3, patient3, "PC-03", "Patient requires a routine pediatric assessment."));

		medicalRecordController.createMedicalRecord(new MedicalRecord(doctor4, patient4, "PC-04",
				"Patient reports persistent knee pain. Orthopedic examination recommended."));

		medicalRecordController.createMedicalRecord(new MedicalRecord(doctor2, patient2, "PC-05",
				"Follow-up examination completed. Cardiac condition remains under observation."));

		medicalRecordController.findAll();

	}

}
