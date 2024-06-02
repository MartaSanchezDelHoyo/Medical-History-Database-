package medicalhistory.database.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import medicalhistory.database.interfaces.AllergiesManager;
import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;
import medicalhistory.database.pojos.Allergies;
import medicalhistory.database.pojos.Doctor;

public class JDBCXMLManager implements XMLManager  {
	
	/** Takes an object and turns it into a xml file
	 * @param The object we want to transform
	 * @return The xml file
	 */
	
	@Override
	public File patient2Xml(Patient patient) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        File file = new File("./xmls/Patient-" + patient.getPatientID() + ".xml");
        marshaller.marshal(patient, file);
        return file;
    }
	
	/** Takes an object and turns it into a xml file
	 * @param The object we want to transform
	 * @return The xml file
	 */
	@Override
	public File doctor2Xml(Doctor d) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        File file = new File("./xmls/Doctor-" + d.getDoctor_id() + ".xml");
        marshaller.marshal(d, file);
        return file;
    }

	/** Takes a xml file and turns it into a object
	 * @param The xml file we want to transform
	 * @return The object
	 */
	@Override
	public Patient xml2Patient(File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Patient patient= (Patient) unmarshaller.unmarshal(file);
		return patient;
	}
	
	/** Takes a xml file and turns it into a object
	 * @param The xml file we want to transform
	 * @return The object
	 */
	@Override
	public Doctor xml2Doctor(File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Doctor doctor= (Doctor) unmarshaller.unmarshal(file);
		return doctor;
	}

	/**
	 * Simple transformation method. You can use it in your project.
	 * @param xmlFile - The xml file we want to build the html of.
	 * @param xsltPath - Absolute path to xslt file.
	 * @param resultDir - Directory where you want to put resulting files.
	 */
	@Override
	public File xml2html(File xmlFile, String xsltPath, String resultDir) {
		
        TransformerFactory tFactory = TransformerFactory.newInstance();
        File resultFile = new File(resultDir); 
        try {
            Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
            transformer.transform(new StreamSource(xmlFile), new StreamResult(resultFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultFile; // Devolver el archivo de salida
    }
}
