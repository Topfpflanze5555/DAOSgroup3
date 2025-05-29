package Configuration.Services;
import Configuration.Exceptions.ConfigurationException;
import Configuration.Models.Configuration;
import org.json.*;
import java.io.*;
import java.util.HashMap;

public class ConfigurationPersistenceService
{
    private static ConfigurationPersistenceService instance;
    private static final File file = new File("configuration.json");
    private Configuration configuration;

    private ConfigurationPersistenceService()
    {
        HashMap<Configuration.MODELS, Configuration.SAVEDTYPE> standardConfig = new HashMap<>();
        standardConfig.put(Configuration.MODELS.Leistung, Configuration.SAVEDTYPE.XML);
        standardConfig.put(Configuration.MODELS.Patient, Configuration.SAVEDTYPE.XML);
        standardConfig.put(Configuration.MODELS.Pflegekraft, Configuration.SAVEDTYPE.XML);
        configuration = new Configuration(standardConfig);

        try {
            file.createNewFile();
            writeFile(configuration);
        }
        catch (IOException e)
        {
            throw new ConfigurationException(e.getMessage());
        }



        //configuration = readFile();
    }
    public static ConfigurationPersistenceService getInstance()
    {
        if (instance == null)
        {
            instance = new ConfigurationPersistenceService();
        }
        return instance;
    }

    public Configuration getConfiguration()
    {
        configuration = readFile();
        return configuration;
    }
    private Configuration readFile() throws ConfigurationException
    {
        try {
            FileReader fr = new FileReader(file);
            JSONTokener jst = new JSONTokener(fr);
            JSONObject json = new JSONObject(jst);
            HashMap<Configuration.MODELS, Configuration.SAVEDTYPE> output = new HashMap<>();
            for (String key : json.toMap().keySet())
            {
                Configuration.MODELS confKey = Configuration.MODELS.valueOf(key);
                output.put(confKey, Configuration.SAVEDTYPE.valueOf(json.getString(key)));
            }
            fr.close();


            return new Configuration(output);

        }
        catch (IOException e) {
            throw new ConfigurationException(e.getMessage());
        }

    }
    private void writeFile(Configuration configuration) throws ConfigurationException
    {
        try {




            JSONObject json = new JSONObject();
            for (Configuration.MODELS confKey : configuration.getConfiguration().keySet())
            {
                json.put(confKey.toString(), configuration.getConfiguration().get(confKey).toString());
            }
            FileWriter fw = new FileWriter(file);
            json.write(fw);
            fw.close();

        }
        catch (IOException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }
}
