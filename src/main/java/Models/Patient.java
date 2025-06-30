package Models;

import DataLayer.DataAccessObjects.File.Services.LocalDateAdapter;
import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;


@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public class Patient
{

  @XmlAttribute(name = "id")
  @CsvBindByName(column = "id")
  private long id;

  @XmlAttribute(name = "vorname")
  @CsvBindByName(column = "vorname")
  private String vorname;

  @XmlAttribute(name = "nachname")
  @CsvBindByName(column = "nachname")
  private String nachname;

  @XmlAttribute(name = "geburtsdatum")
  @CsvBindByName(column = "geburtsdatum")
  private String geburtsdatum;

  @XmlAttribute(name = "pflegegrad")
  @CsvBindByName(column = "pflegegrad")
  private int pflegegrad;

  @XmlAttribute(name = "zimmer")
  @CsvBindByName(column = "zimmer")
  private String zimmer;

  @XmlAttribute(name = "vermoegen")
  @CsvBindByName(column = "vermoegen")
  private double vermoegen;

  public Patient()
  {
  }

  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public String getVorname()
  {
    return vorname;
  }

  public void setVorname(String vorname)
  {
    this.vorname = vorname;
  }

  public String getNachname()
  {
    return nachname;
  }

  public void setNachname(String nachname)
  {
    this.nachname = nachname;
  }

  public String getGeburtsdatum()
  {
    return geburtsdatum;
  }

  public void setGeburtsdatum(String geburtsdatum)
  {
    this.geburtsdatum = geburtsdatum;
  }

  public int getPflegegrad()
  {
    return pflegegrad;
  }

  public void setPflegegrad(int pflegegrad)
  {
    this.pflegegrad = pflegegrad;
  }

  public String getZimmer()
  {
    return zimmer;
  }

  public void setZimmer(String zimmer)
  {
    this.zimmer = zimmer;
  }

  public double getVermoegen()
  {
    return vermoegen;
  }

  public void setVermoegen(double vermoegen)
  {
    this.vermoegen = vermoegen;
  }
}
