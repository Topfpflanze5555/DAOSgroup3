package TestClasses;

import Configuration.Services.ConfigurationPersistenceService;
import DataLayer.Services.DataLayerFactory;
import DataLayer.Services.IDataLayer;
import Models.Leistung;
import Models.Patient;
import Models.Pflegekraft;

import java.time.LocalDate;

public class DataLayerTest {
    public static void main(String[] args) {
        DataLayerFactory dataLayerFactory = new DataLayerFactory(ConfigurationPersistenceService.getInstance().getConfiguration());
        IDataLayer dataLayer = dataLayerFactory.createDataLayer();

        Leistung leistung = new Leistung();
        leistung.setLkNr("1");
        leistung.setBezeichnung("bla");
        leistung.setBeschreibung("blabla");

        Patient patient = new Patient();
        patient.setGeburtsdatum(LocalDate.of(1890,4,20).toString());
        patient.setId(1);
        patient.setNachname("Böhmermann");
        patient.setVorname("Jan");
        patient.setPflegegrad(2);
        patient.setVermoegen(2^3);
        patient.setZimmer("Badezimmer");


        Pflegekraft pflegekraft = new Pflegekraft();
        pflegekraft.setId(2);
        pflegekraft.setNachname("Merkel");
        pflegekraft.setVorname("Angela");
        pflegekraft.setTelefon("04792800600");

        dataLayer.getDaoLeistung().create(leistung);
        dataLayer.getDaoPatient().create(patient);
        dataLayer.getDaoPflegekraft().create(pflegekraft);

    }
}
