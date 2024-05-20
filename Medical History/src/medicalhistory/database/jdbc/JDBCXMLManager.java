package medicalhistory.database.jdbc;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.pojos.Patient;

public class JDBCXMLManager implements XMLManager  {

	@Override
	public File patient2Xml(Patient p) {
		// TODO Auto-generated method stub
		return null;
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
