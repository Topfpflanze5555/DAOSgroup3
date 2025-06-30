package Models;

import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "pflegekraft")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pflegekraft
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

  @XmlAttribute(name = "telefon")
  @CsvBindByName(column = "telefon")
  private String telefon;

  public Pflegekraft(){}

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

  public String getTelefon()
  {
    return telefon;
  }

  public void setTelefon( String telefon)
  {
    this.telefon = telefon;
  }
}
