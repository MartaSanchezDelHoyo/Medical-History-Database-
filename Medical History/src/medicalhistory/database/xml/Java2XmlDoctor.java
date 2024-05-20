package medicalhistory.database.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.*;
import medicalhistory.database.pojos.Doctor;

public class Java2XmlDoctor {

	
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	private static void printReports() {
		Query q1 = em.createNativeQuery("SELECT * FROM reports", Doctor.class);
		List<Doctor> docotors = (List<Doctor>) q1.getResultList();
		for (Doctor docotor : docotors) {
			System.out.println(docotor);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		printReports();
		System.out.print("Choose a report to turn into an XML file:");
		int rep_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM reports WHERE id = ?", Doctor.class);
		q2.setParameter(1, rep_id);
		Doctor patient = (Doctor) q2.getSingleResult();
		
		File file = new File("./xmls/Sample-Report.xml");
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);

	}
}
