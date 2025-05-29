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
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Configuration:\n");
        for (MODELS m : MODELS.values()) {
            str.append(m.toString()).append("\n");
            str.append(getConfiguration().get(m).toString()).append("\n");
        }
        return str.toString();
    }




}
