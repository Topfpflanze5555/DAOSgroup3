package DataLayer.DataAccessObjects.File.Services;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class XmlLocalDateAdapter extends XmlAdapter<String, LocalDate> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  XmlLocalDateAdapter()
  {

    super();
    formatter.format(LocalDate.now());
  }
  XmlLocalDateAdapter(LocalDate localDate)
  {
    super();
    formatter.format(localDate);
  }

  @Override
  public LocalDate unmarshal(String string) throws Exception {
    return LocalDate.parse(string, formatter);
  }

  @Override
  public String marshal(LocalDate localDate) throws Exception {
    return localDate.format(formatter);
  }
}
