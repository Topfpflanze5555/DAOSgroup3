package Models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Leistung")
public class Leistung {
  private String lkNr;
  private String bezeichnung;
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
