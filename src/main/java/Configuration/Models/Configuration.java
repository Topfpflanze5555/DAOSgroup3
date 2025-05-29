package Configuration.Models;


import java.util.HashMap;

public class Configuration {
    public enum MODELS { Leistung, Patient, Pflegekraf}
    public enum SAVEDTYPE {XML, CSV, SQLITE}
    private final HashMap<MODELS, SAVEDTYPE> configuration = new HashMap<>();
    public Configuration(SAVEDTYPE configLeistung, SAVEDTYPE configPatient, SAVEDTYPE configPflegekraf)
    {
        configuration.put(MODELS.Leistung, configLeistung);
        configuration.put(MODELS.Patient, configPatient);
        configuration.put(MODELS.Pflegekraf, configPflegekraf);
    }

    public HashMap<MODELS, SAVEDTYPE> getConfiguration() {
        return configuration;
    }




}
