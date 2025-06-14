package DataLayer.DataAccessObjects.File.DAOS;
import java.nio.file.Path;

import DataLayer.DataAccessObjects.File.Services.IFIlePersistenceService;
import Models.Patient;

public class PatientDaoFile extends AbstractDaoFile<Patient, Long> {

	PatientDaoFile(IFIlePersistenceService<Patient> filePersistenceService,  Path filePath) {
		super(filePersistenceService, Patient.class, filePath);
		
	}

	@Override
	protected Long getIdFromObject(Patient object) {
		
		return object.getId();
	}

	@Override
	protected void setIdToObjectToInsert(Patient objectToInsert) {
		long id = 1;
		for (Patient patient : read())
		{
			if (id <= patient.getId())
			{
				id = patient.getId()+1;
			}
		}
		objectToInsert.setId(id);
		return;
		
	}
}
