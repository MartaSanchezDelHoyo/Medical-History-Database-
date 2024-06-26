package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Visit;

public interface MedicationManager {
	
	public void addMedication (Medication entry);
	public void linkMedicationToManufacturer(Medication entry, Manufacturer manu);
	public void addManufacturer (Manufacturer entry);
	public void modifyMedication (Medication entry); 
    public Medication showMedication(Visit toSearch);
    public Medication showMedication(String name);
    public List<Medication> showMedications(int visit_id);
    public List<Medication> showMedicationsByManufacturer(int manufacturer_id);
    public List<Manufacturer> showManufacturers(int medication_id);
    public List<Manufacturer> showManufacturerWithMedications(int manufacturerId);
    
}
