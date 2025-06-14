package DataLayer.DataAccessObjects.File.DAOS;
import java.nio.file.Path;

import DataLayer.DataAccessObjects.File.Services.IFIlePersistenceService;
import Models.Leistung;


public class LeistungDaoFile extends AbstractDaoFile<Leistung, String> 
{

	LeistungDaoFile(IFIlePersistenceService<Leistung> filePersistenceService, Path filePath)
	{
		super(filePersistenceService, Leistung.class, filePath);
		
		
	}
	
	@Override
	protected String getIdFromObject(Leistung object) {
		return object.getLkNr();
	}
	@Override
	protected void setIdToObjectToInsert(Leistung objectToInsert) {
		long lkNr = 1;
		for (Leistung leistung : read())
		{
			if (lkNr <= Long.parseLong(leistung.getLkNr()))
			{
				lkNr = Long.parseLong(leistung.getLkNr())+1;
			}
		}
		objectToInsert.setLkNr(Long.toString(lkNr));
		return;
		
	}

	
}
