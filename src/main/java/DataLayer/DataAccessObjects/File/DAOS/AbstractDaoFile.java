package DataLayer.DataAccessObjects.File.DAOS;
import DataLayer.DataAccessObjects.IDao;

import java.nio.file.*;
import java.util.*;

public abstract class AbstractDaoFile<T, ID> implements IDao<T, ID>
{
	
	private DataLayer.DataAccessObjects.File.Services.IFilePersistenceService<T> filePersistenceService;
	private Class<T> objectType;
	private Path filePath;
	protected List<T> cachedObjectList;
	
	AbstractDaoFile(DataLayer.DataAccessObjects.File.Services.IFilePersistenceService<T> filePersistenceService, Class<T> objectType, Path filePath)
	{
		this.filePersistenceService = filePersistenceService;
		this.objectType = objectType;
		this.filePath = filePath;
	}
	@Override
	public T create(T objectToInsert)
	{
		loadObjectList();
		setIdToObjectToInsert(objectToInsert);
		cachedObjectList.add(objectToInsert);
		saveObjectList(cachedObjectList);
		return objectToInsert;
	}
	@Override
	public T read(ID id)
	{
		
		
		for (T object : loadObjectList())
		{
			if (hasMatchingId(object, id))
			{
				return object;
			}
			
		}
			
		
		throw new DataLayer.Exceptions.DAOException("Object isn't listed");
		
	}
	@Override
	public List<T> read()
	{
		return loadObjectList();
	}
	@Override
	public void update(T objectToUpdate)
	{
		loadObjectList();
		for (T object : cachedObjectList)
		{
			if (getIdFromObject(object).equals(getIdFromObject(objectToUpdate)))
			{
				cachedObjectList.add(objectToUpdate);
				saveObjectList(cachedObjectList);
				return;
			}
		}
		
		throw new DataLayer.Exceptions.DAOException("object does not exists in file");
		
	}
	@Override
	public void delete(ID id)
	{
		cachedObjectList.remove(read(id));
		saveObjectList(cachedObjectList);
		return;
	}
	protected abstract ID getIdFromObject(T object);
	
	protected abstract void  setIdToObjectToInsert(T objectToInsert);
	
	private boolean hasMatchingId(T object, ID id)
	{
		return getIdFromObject(object).equals(id);
	}
	private List<T> loadObjectList()
	{
		cachedObjectList = filePersistenceService.readFile(objectType, filePath);
		return cachedObjectList;
	}
	private void saveObjectList(List<T> objectList)
	{
		filePersistenceService.writeFile(objectType, objectList, filePath);
		
	}
		
	
}
