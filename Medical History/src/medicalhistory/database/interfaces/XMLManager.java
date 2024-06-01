package medicalhistory.database.interfaces;

import java.io.File;

import javax.xml.bind.JAXBException;

import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Doctor;

public interface XMLManager {

	public File patient2Xml (Patient p) throws JAXBException ;
	public File doctor2Xml (Doctor d) throws JAXBException;
	public Patient xml2Patient (File file) throws JAXBException;
	public Doctor xml2Doctor (File file) throws JAXBException;
	public void xml2html(String sourcePath, String xsltPath,String resultDir);
}
