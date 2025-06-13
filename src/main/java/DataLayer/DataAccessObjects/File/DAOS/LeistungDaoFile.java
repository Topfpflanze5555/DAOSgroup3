package DataLayer.DataAccessObjects.File.DAOS;
import java.nio.file.Path;

import DataLayer.DataAccessObjects.File.Services.IFIlePersistenceService;
import Models.Leistung;

public class LeistungDaoFile<Leistung, String> extends AbstractDaoFile 
{

	LeistungDaoFile(IFIlePersistenceService filePersistenceService, Class objectType, Path filePath) 
	{
		super(filePersistenceService, objectType, filePath);
		
	}
	@Override
	protected String getIdFromObject(Leistung object)
	{
		return object.getLkNr();
	}
	@Override
	protected void  setIdToObjectToInsert(Leistung objectToInsert)
	{
		
	}

	
}
