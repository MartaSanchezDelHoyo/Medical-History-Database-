package medicalhistory.database.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.*;
import medicalhistory.database.pojos.Patient;

public class Java2XmlPatient {

	
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	private static void printReports() {
		Query q1 = em.createNativeQuery("SELECT * FROM reports", Patient.class);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		for (Patient patient : patients) {
			System.out.println(patient);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		printReports();
		System.out.print("Choose a report to turn into an XML file:");
		int rep_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM reports WHERE id = ?", Patient.class);
		q2.setParameter(1, rep_id);
		Patient patient = (Patient) q2.getSingleResult();
		
		File file = new File("./xmls/Sample-Report.xml");
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);

	}
}
