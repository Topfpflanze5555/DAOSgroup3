package DataLayer.DataAccessObjects.File.Services;

import DataLayer.Exceptions.DAOException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FilePersistenceServiceCsv<T> implements IFilePersistenceService<T> {

  private char separator;

  public FilePersistenceServiceCsv(char separator)
  {
    this.separator = separator;
  }

  private String[] getCsvColumnNames(Class<T> classType)
  {
    List<String> columnList = new ArrayList<>();
    // alle Attribute durchlaufen
    for ( Field field : classType.getDeclaredFields())
    {
      if (field.isAnnotationPresent(CsvBindByName.class))
      {
        CsvBindByName annotation = field.getAnnotation(CsvBindByName.class);
        columnList.add(annotation.column());
      }
    }

    return columnList.toArray(new String[0]);
  }

  @Override
  public List<T> readFile( Class<T> classType,Path filePath) throws DAOException
  {
    try (Reader reader = new FileReader(filePath.toFile()))
    {
      CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
        .withType(classType)
        .withIgnoreLeadingWhiteSpace(true)
        .withSeparator(separator)
        .build();

      return csvToBean.parse();
    }
    catch (IOException e)
    {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public void writeFile(Class<T> classType,List<T> listToPersist, final Path filePath) throws DAOException
  {
    ColumnPositionMappingStrategy<T> mappingStrategy = new ColumnPositionMappingStrategy<>();
    mappingStrategy.setType(classType);
    String[] columns = getCsvColumnNames(classType);
    mappingStrategy.setColumnMapping(columns);

    String header = String.join(String.valueOf(separator), columns) + "\n";

    try (Writer writer = new FileWriter(filePath.toFile()))
    {
      writer.write(header);
      StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
        .withSeparator(separator)
        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
        .withMappingStrategy(mappingStrategy)
        .build();
      beanToCsv.write(listToPersist);
    } catch (Exception e)
    {
      throw new DAOException(e.getMessage());
    }


  }
}
