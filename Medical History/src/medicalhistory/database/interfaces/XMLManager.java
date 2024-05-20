package medicalhistory.database.interfaces;

import java.io.File;

import medicalhistory.database.pojos.Patient;

public interface XMLManager {

	public File patient2Xml (Patient p);
	public Patient xml2Patient (File f);
	public void xml2html(File xml, File xslt, File html);
}
