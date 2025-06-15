package DataLayer.DataAccessObjects.File.DAOS;
import java.nio.file.Path;

import DataLayer.DataAccessObjects.File.Services.IFilePersistenceService;
import Models.Pflegekraft;

public class PflegekraftDaoFile extends AbstractDaoFile<Pflegekraft, Long> {

	public PflegekraftDaoFile(IFilePersistenceService<Pflegekraft> filePersistenceService, Path filePath) {
		super(filePersistenceService, Pflegekraft.class, filePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Long getIdFromObject(Pflegekraft object) {
		return object.getId();
	}

	@Override
	protected void setIdToObjectToInsert(Pflegekraft objectToInsert) {
		long id = 1;
		for (Pflegekraft pflegekraft : read())
		{
			if (id <= pflegekraft.getId())
			{
				id = pflegekraft.getId()+1;
			}
		}
		objectToInsert.setId(id);
		
	}
}
