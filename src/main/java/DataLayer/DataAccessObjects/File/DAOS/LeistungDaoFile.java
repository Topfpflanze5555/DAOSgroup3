package DataLayer.DataAccessObjects.File.DAOS;
import java.nio.file.Path;
import java.util.UUID;

import DataLayer.DataAccessObjects.File.Services.IFilePersistenceService;
import Models.Leistung;


public class LeistungDaoFile extends AbstractDaoFile<Leistung, String> 
{

	public LeistungDaoFile(IFilePersistenceService<Leistung> filePersistenceService, Path filePath)
	{
		super(filePersistenceService, Leistung.class, filePath);
		
		
	}
	
	@Override
	protected String getIdFromObject(Leistung object) {
		return object.getLkNr();
	}
	@Override
	protected void setIdToObjectToInsert(Leistung objectToInsert) {
		objectToInsert.setLkNr(UUID.randomUUID().toString());
	}

	
}
