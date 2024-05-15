package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Allergies;

public interface AllergiesManager {
	public void changeAllergy (Allergies allergies);
	 public Allergies getAllergy(int allergy_id );
	 public List<Allergies> getAllergies(int patient_id);
}
