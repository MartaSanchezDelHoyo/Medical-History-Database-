package medicalhistory.database.jdbc;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.pojos.Patient;

public class JDBCXMLManager implements XMLManager  {

	@Override
	public File patient2Xml(Patient patient) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        File file = new File("./xmls/Patient-" + patient.getPatientID() + ".xml");
        marshaller.marshal(patient, file);
        return file;
    }

	@Override
	public Patient xml2Patient(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Simple transformation method. You can use it in your project.
	 * @param sourcePath - Absolute path to source xml file.
	 * @param xsltPath - Absolute path to xslt file.
	 * @param resultDir - Directory where you want to put resulting files.
	 */
	@Override
	public void xml2html(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
