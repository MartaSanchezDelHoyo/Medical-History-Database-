package medicalhistory.database.interfaces;

import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Visit;

public interface MedicationManager {
	
	public void addMedication (Medication entry);
	public void addManufacturer (Manufacturer entry);
	public void modifyMedication (Medication entry, Integer medicationID); 
    public Medication showMedication(Visit toSearch);
    
}
