package medicalhistory.database.xml;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Allergies;


public class Xml2JavaPatient {

	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./xmls/External-Patient.xml");
		Patient patient = (Patient) unmarshaller.unmarshal(file);

		// Print the report
		System.out.println("Patient:");
		System.out.println("Name: " + patient.getPatientName());
		System.out.println("Date of birth: " + patient.getDateofbirth()); //duda
		System.out.println("Bloodtype: " + patient.getBloodtype());
		System.out.println("Email: " + patient.getEmail());
		System.out.println("Username: " + patient.getUsername());
		List<Allergies> alles = patient.getAllergies();
		for (Allergies alle : alles) {
			System.out.println("Allergie: " + alle.getAllergiesName());
		}
		List<Doctor> doctors = patient.getDoctors();
		for (Doctor doctor : doctors) {
			System.out.println("Doctor name: " + doctor.getName());
			System.out.println("Doctor surname: " + doctor.getSurname());
		}

		// Store the report in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		for (Allergies allergie : alles) {
			em.persist(allergie);
		}
		em.persist(patient);
		
		// End transaction
		tx1.commit();
	}
}
