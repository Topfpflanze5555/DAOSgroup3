package Configuration.Services;
import Configuration.Exceptions.ConfigurationException;
import Configuration.Models.Configuration;
import Configuration.Models.Configuration.MODELS;

import org.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigurationPersistenceService
{
    private static ConfigurationPersistenceService instance;
    private static final File file = new File("configuration.json");
    private Configuration configuration;

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
        
        return configuration;
    }
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


            return new Configuration(output);

        }
        catch (IOException | JSONException e) {
            throw new ConfigurationException(e.getMessage());
        }

    }
    private void writeFile(Configuration configuration) throws ConfigurationException
    {
        try {




            JSONObject json = new JSONObject();
            JSONArray jarr = new JSONArray();

            for (MODELS model : MODELS.values())
            {
            	HashMap<String, String> modelMap = new HashMap<>(); 
            	modelMap.put("Name", model.name());
            	modelMap.put("SaveType", configuration.getSavedType(model));
            	modelMap.put("URL", configuration.getURL(model));
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
    private Configuration standardConfig()
    {
        
        return new Configuration(Configuration.SAVEDTYPE.XML, "./Temp.xml", Configuration.SAVEDTYPE.XML, "./Temp.xml", Configuration.SAVEDTYPE.XML, "./Temp.xml");
    }
}
