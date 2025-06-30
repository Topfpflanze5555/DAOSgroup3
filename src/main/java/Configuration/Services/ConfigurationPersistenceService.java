package Configuration.Services;
import Configuration.Exceptions.ConfigurationException;
import Configuration.Models.Configuration;
import Configuration.Models.Configuration.MODELS;

import org.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Persistence service for the Configuration Model
 */
public class ConfigurationPersistenceService
{
    private static ConfigurationPersistenceService instance;
    private static final File file = new File("configuration.json");
    private Configuration configuration;

    /**
     * constructor SingletonPattern
     */
    private ConfigurationPersistenceService()
    {

        try {
            if (file.createNewFile()) {
                configuration = standardConfig();
                writeFile(configuration);
            }
            else
            {
                configuration = readFile();
            }
        }
        catch (IOException e)
        {
            throw new ConfigurationException(e.getMessage());
        }




    }

    /**
     * Singleton Pattern
     * @return Instance of this class
     */
    public static ConfigurationPersistenceService getInstance()
    {
        if (instance == null)
        {
            instance = new ConfigurationPersistenceService();
        }
        return instance;
    }

    /**
     *
     * @return configuration variable
     */
    public Configuration getConfiguration()
    {
        
        return configuration;
    }

    /**
     *
     * @return configuration Model that has been read
     * @throws ConfigurationException when file does not exist or is not a json
     */
    private Configuration readFile() throws ConfigurationException
    {
        try {
            FileReader fr = new FileReader(file);
            JSONTokener jst = new JSONTokener(fr);
            JSONObject json = new JSONObject(jst);
            ArrayList<HashMap<String, String>> output = new ArrayList<>();
            
            JSONArray jarr = json.getJSONArray("Model");
            for (int i =0; i<jarr.length(); i++)
            {
            	JSONObject jobj = jarr.getJSONObject(i);
            	HashMap<String, String> outHashMap = new HashMap<>();
            	for (String key : jobj.keySet())
            	{
            		outHashMap.put(key, jobj.getString(key));
            	}
            	output.add(outHashMap);
            }
            fr.close();

            configuration = new Configuration(output);
            return configuration;

        }
        catch (IOException | JSONException e) {
            throw new ConfigurationException(e.getMessage());
        }

    }

    /**
     *
     * @param configuration configuration Model to be written into file
     * @throws ConfigurationException if the file can't be written
     */
    private void writeFile(Configuration configuration) throws ConfigurationException
    {
        try {




            JSONObject json = new JSONObject();
            JSONArray jarr = new JSONArray();

            for (MODELS model : MODELS.values())
            {
            	HashMap<String, String> modelMap = new HashMap<>(); 
            	modelMap.put(Configuration.MODELPARAMS.Name.name(), model.name());
            	modelMap.put(Configuration.MODELPARAMS.SaveType.name(), configuration.getSavedType(model));
            	modelMap.put(Configuration.MODELPARAMS.URL.name(), configuration.getURL(model));
            	jarr.put(modelMap);
            }
            json.put("Model", jarr);
            FileWriter fw = new FileWriter(file);
            json.write(fw);
            fw.close();

        }
        catch (IOException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }

    /**
     * load standard configuration
     * @return standard configuration
     */
    private Configuration standardConfig()
    {
        
        return new Configuration(Configuration.SAVEDTYPE.XML, "./Temp1.xml", Configuration.SAVEDTYPE.XML, "./Temp2.xml", Configuration.SAVEDTYPE.XML, "./Temp3.xml");
    }
}
