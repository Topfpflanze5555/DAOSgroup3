package DataLayer.DataAccessObjects.File.Services;

import DataLayer.Exceptions.DAOException;

import java.nio.file.Path;
import java.util.List;


public interface IFilePersistenceService<T>
{

  List<T> readFile(Class<T> classType, Path filePath) throws DAOException;

  void writeFile(Class<T> classType, List<T> listToPersist, Path filePath) throws DAOException;

}
