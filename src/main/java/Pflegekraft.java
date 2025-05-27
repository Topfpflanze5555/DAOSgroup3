package DAOSgroup3.src.main.java;

public class Pflegekraft
{
  private long id;
  private String vorname;
  private String nachname;
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
}
