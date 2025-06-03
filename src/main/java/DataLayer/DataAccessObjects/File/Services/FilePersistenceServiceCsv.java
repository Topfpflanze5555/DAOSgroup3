package DataLayer.DataAccessObjects.File.Services;

import DataLayer.Exceptions.DAOException;

import java.nio.file.Path;
import java.util.List;


public class FilePersistenceServiceCsv<T> implements IFIlePersistenceService<T> {

  private char separator;

  public FilePersistenceServiceCsv(char separator)
  {

  }

  @Override
  public List<T> readFile( Class<T> classType,Path filePath) throws DAOException
  {
    return List.of();
  }

  @Override
  public void writeFile(Class<T> classType,List<T> listToPersist, final Path filePath)
    throws DAOException
  {

  }
}
