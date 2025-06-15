package DataLayer.DataAccessObjects.File.Services;

import DataLayer.Exceptions.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
      XmlWrapper<T> wrapper = (XmlWrapper<T>) unmarshaller.unmarshal(filePath.toFile());
      if (wrapper.getItems() == null)
      {
        return new ArrayList<>();
      }
      return wrapper.getItems();
    } catch (JAXBException e)
    {
      throw new DAOException(e.getMessage());
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
