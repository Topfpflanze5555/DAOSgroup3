package Configuration.Models;


import java.util.HashMap;

public class Configuration {
    public enum MODELS { Leistung, Patient, Pflegekraft}
    public enum SAVEDTYPE {XML, CSV, SQLITE}
    private final HashMap<MODELS, SAVEDTYPE> configuration = new HashMap<>();
    public Configuration(HashMap<MODELS, SAVEDTYPE> configuration)
    {
        this.configuration.putAll(configuration);
    }

    public HashMap<MODELS, SAVEDTYPE> getConfiguration() {
        return configuration;
    }




}
