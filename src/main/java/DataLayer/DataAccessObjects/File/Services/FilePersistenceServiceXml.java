package DataLayer.DataAccessObjects.File.Services;

import DataLayer.Exceptions.DAOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FilePersistenceServiceXml<T> implements IFilePersistenceService<T> {

  @Override
  public List<T> readFile(final Class<T> classType, final Path filePath) throws DAOException
  {
    try
    {
      JAXBContext context = JAXBContext.newInstance(XmlWrapper.class, classType);
      Unmarshaller unmarshaller = context.createUnmarshaller();

      File FileToWrite = filePath.toFile();
      if(!FileToWrite.exists() || FileToWrite.length() == 0) {
        return new ArrayList<>();
      }

      XmlWrapper<T> wrapper = (XmlWrapper<T>) unmarshaller.unmarshal(FileToWrite);
      if (wrapper.getItems() == null)
      {
        return new ArrayList<>();
      }
      return wrapper.getItems();
    } catch (Exception e)
    {
      throw new RuntimeException(e.getMessage());
    }

  }

  @Override
  public void writeFile(final Class<T> classType, final List<T> listToPersist, final Path filePath) throws DAOException
  {
    try
    {
      JAXBContext context = JAXBContext.newInstance(XmlWrapper.class, classType);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      XmlWrapper<T> wrapper = new XmlWrapper<>(listToPersist);
      marshaller.marshal(wrapper, filePath.toFile());
    } catch (JAXBException e)
    {
      throw new DAOException(e.getMessage());
    }

  }
}
