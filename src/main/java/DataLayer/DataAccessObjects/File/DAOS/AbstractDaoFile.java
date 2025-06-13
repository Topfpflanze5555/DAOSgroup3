package DataLayer.DataAccessObjects.File.DAOS;
import DataLayer.DataAccessObjects.File.Services.*;

import java.nio.file.*;
import java.util.*;

public abstract class AbstractDaoFile<T, ID> 
{
	
	private DataLayer.DataAccessObjects.File.Services.IFIlePersistenceService<T> filePersistenceService;
	private Class<T> objectType;
	private Path filePath;
	protected List<T> cachedObjectList;
	
	AbstractDaoFile(DataLayer.DataAccessObjects.File.Services.IFIlePersistenceService<T> filePersistenceService, Class<T> objectType, Path filePath)
	{
		this.filePersistenceService = filePersistenceService;
		this.objectType = objectType;
		this.filePath = filePath;
	}
	public T create(T objectToInsert)
	{
		if (loadObjectList().contains(objectToInsert))
		{
			throw new DataLayer.Exceptions.DAOException("Object couldn't be written or is listed already");
		}
		cachedObjectList.add(objectToInsert);
		return objectToInsert;
	}
	public T read(ID id)
	{
		
		
		for (T object : loadObjectList())
		{
			if (hasMatchingId(object, id))
			{
				return object;
			}
		}
			
		
		throw new DataLayer.Exceptions.DAOException("Object couldn't be read or isn't listed");
		
	}
	public void update(T objectToUpdate)
	{
		
	}
	public void delete(ID id)
	{
		
	}
	protected abstract ID getIdFromObject(T object);
	
	protected abstract void  setIdToObjectToInsert(T objectToInsert);
	
	private boolean hasMatchingId(T object, ID id)
	{
		return getIdFromObject(object).equals(id);
	}
	private List<T> loadObjectList()
	{
		return cachedObjectList;
	}
	private void saveObjectList(List<T> objectList)
	{
		
	}
		
	
}
