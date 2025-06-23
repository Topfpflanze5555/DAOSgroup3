package Configuration.Models;

import java.util.*;

import Configuration.Exceptions.ConfigurationException;

/**
 * Model class for the configuration
 */
public class Configuration {
    public enum MODELS { Leistung, Patient, Pflegekraft}
    public enum SAVEDTYPE {XML, CSV, SQLITE}
    public enum MODELPARAMS {Name, SaveType, URL}
    private ArrayList<HashMap<String, String>> configuration = new ArrayList<>();

    /**
     * constructor
     * @param configuration a configuration model as an ArrayList of HashMaps
     *                      ArrayList has all Models as HashMap
     *                      HashMap has the values Name, SavedType, URL
     */
    public Configuration(ArrayList<HashMap<String, String>> configuration)
    {
        this.configuration = configuration;
    }

    /**
     * constructor currently sets all Models with a save type and a URL
     */
    public Configuration(SAVEDTYPE saveTypeLeistung, String urlLeistung,
    		SAVEDTYPE saveTypePflegekraft, String urlPflegekraft,
    		SAVEDTYPE saveTypePatient, String urlPatient)
    {
        HashMap<String, String> leistungHashMap = new HashMap<>();
        HashMap<String, String> pflegekraftHashMap = new HashMap<>();
        HashMap<String, String> patientHashMap = new HashMap<>();
        
        leistungHashMap.put(Configuration.MODELPARAMS.Name.name(), MODELS.Leistung.name());
        leistungHashMap.put(Configuration.MODELPARAMS.SaveType.name(), saveTypeLeistung.name());
        leistungHashMap.put(Configuration.MODELPARAMS.URL.name(), urlLeistung);
        
        pflegekraftHashMap.put(Configuration.MODELPARAMS.Name.name(), MODELS.Pflegekraft.name());
        pflegekraftHashMap.put(Configuration.MODELPARAMS.SaveType.name(), saveTypePflegekraft.name());
        pflegekraftHashMap.put(Configuration.MODELPARAMS.URL.name(), urlPflegekraft);
        
        patientHashMap.put(Configuration.MODELPARAMS.Name.name(), MODELS.Patient.name());
        patientHashMap.put(Configuration.MODELPARAMS.SaveType.name(), saveTypePatient.name());
        patientHashMap.put(Configuration.MODELPARAMS.URL.name(), urlPatient);
        
        configuration.add(leistungHashMap);
        configuration.add(pflegekraftHashMap);
        configuration.add(patientHashMap);
        
    }

    /**
     *
     * @return the Configuration as an ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> getConfiguration() {
        return configuration;
    }
    public HashMap<String, String> getModel(MODELS model) throws ConfigurationException 
    {
    	for(HashMap<String, String> Hmodel : configuration)
    	{
    		if (Hmodel.get(Configuration.MODELPARAMS.Name.name()).equals(model.name()))
    		{
    			return Hmodel;
    		}
    	}
    	throw new ConfigurationException("No Configuration for "+model.name());
    }

    /**
     * Set Configuration for model
     * @param model the model class for which the configuration is made
     * @param savedType the save type that is used to read/write the model data from and to a location
     * @param url the place where the model data is stored (either Filepath or URL)
     *
     */
    public void setModel(MODELS model, SAVEDTYPE savedType, String url)
    {
    	HashMap<String,String> modelHashMap = new HashMap<>();
    	
    	modelHashMap.put(Configuration.MODELPARAMS.Name.name(), model.name());
    	modelHashMap.put(Configuration.MODELPARAMS.SaveType.name(), savedType.name());
    	modelHashMap.put(Configuration.MODELPARAMS.URL.name(), url);
    	removeModel(model);
    	configuration.add(modelHashMap);
    	
    	
    }

    /**
     *
     * @param model Model from which the Name is extracted
     * @return Name from the model
     */
    public String getName(MODELS model)
    {
    	return getModel(model).get(Configuration.MODELPARAMS.Name.name());
    }

    /**
     *
     * @param model Model from which the SavedType is extracted
     * @return SavedType from the model
     */
    public String getSavedType(MODELS model)
    {
    	return getModel(model).get(Configuration.MODELPARAMS.SaveType.name());
    }

    /**
     *
     * @param model Model from which the SavedType is extracted
     * @return SavedType from the model
     */
    public String getURL(MODELS model)
    {
    	return getModel(model).get(Configuration.MODELPARAMS.URL.name());
    }

    /**
     *
     * @param model model which gets removed from the configuration
     */
    private void removeModel(MODELS model)
    {
    	for(int i = 0; i<configuration.size();i++)
    	{
    		if (configuration.get(i).get(Configuration.MODELPARAMS.Name.name()).equals(model.name()))
    		{
    			configuration.remove(i);
    		}
    	}
    }

    /**
     *
     * @return String in format "Name
     * SaveType
     * URL"
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for (MODELS m : MODELS.values())
        {
            str.append(getName(m));
            str.append("\n");
            str.append(getSavedType(m));
            str.append("\n");
            str.append(getURL(m));
            str.append("\n");
        }
        return str.toString();
    }
    
    




}
