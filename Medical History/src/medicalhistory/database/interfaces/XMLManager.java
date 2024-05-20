package medicalhistory.database.interfaces;

import java.io.File;

import medicalhistory.database.pojos.Patient;

public interface XMLManager {

	public File patient2Xml (Patient p);
	public Patient xml2Patient (String filepath);
	public void xml2html(String sourcePath, String xsltPath,String resultDir);
}
