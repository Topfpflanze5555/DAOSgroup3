package Models;

import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Leistung")
@XmlAccessorType(XmlAccessType.FIELD)
public class Leistung {

  @XmlAttribute(name = "lkNr")
  @CsvBindByName(column = "lknr")
  private String lkNr;

  @XmlAttribute(name = "bezeichnung")
  @CsvBindByName(column = "bezeichnung")
  private String bezeichnung;

  @XmlAttribute(name = "beschreibung")
  @CsvBindByName(column = "beschreibung")
  private String beschreibung;

  public String getLkNr()
  {
    return lkNr;
  }

  public void setLkNr(String lkNr)
  {
    this.lkNr = lkNr;
  }

  public String getBezeichnung()
  {
    return bezeichnung;
  }

  public void setBezeichnung(String bezeichnung)
  {
    this.bezeichnung = bezeichnung;
  }

  public String getBeschreibung()
  {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung)
  {
    this.beschreibung = beschreibung;
  }
}
