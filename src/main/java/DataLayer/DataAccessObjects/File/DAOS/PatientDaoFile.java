package DataLayer.DataAccessObjects.File.DAOS;
import java.nio.file.Path;

import DataLayer.DataAccessObjects.File.Services.IFilePersistenceService;
import Models.Patient;

public class PatientDaoFile extends AbstractDaoFile<Patient, Long> {

	public PatientDaoFile(IFilePersistenceService<Patient> filePersistenceService,  Path filePath) {
		super(filePersistenceService, Patient.class, filePath);
		
	}

	@Override
	protected Long getIdFromObject(Patient object) {
		
		return object.getId();
	}

	@Override
	protected void setIdToObjectToInsert(Patient objectToInsert) {
		long id = 1;

		try {
			for (Patient patient : read()) {
				if (id <= patient.getId()) {
					id = patient.getId() + 1;
				}
			}
			objectToInsert.setId(id);
		} catch (Exception e) {
			//
		}
		return;
		
	}
}
