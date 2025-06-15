package Configuration.Models;

import java.util.*;

import Configuration.Exceptions.ConfigurationException;

public class Configuration {
    public enum MODELS { Leistung, Patient, Pflegekraft}
    public enum SAVEDTYPE {XML, CSV, SQLITE}
    public enum MODELPARAMS {Name, SaveType, URL}
    private ArrayList<HashMap<String, String>> configuration = new ArrayList<>();
    public Configuration(ArrayList<HashMap<String, String>> configuration)
    {
        this.configuration = configuration;
    }
    public Configuration(SAVEDTYPE saveTypeLeistung, String urlLeistung,
    		SAVEDTYPE saveTypePflegekraft, String urlPflegekraft,
    		SAVEDTYPE saveTypePatient, String urlPatient)
    {
        HashMap<String, String> leistungHashMap = new HashMap<>();
        HashMap<String, String> pflegekraftHashMap = new HashMap<>();
        HashMap<String, String> patientHashMap = new HashMap<>();
        
        leistungHashMap.put("Name", MODELS.Leistung.name());
        leistungHashMap.put("SaveType", saveTypeLeistung.name());
        leistungHashMap.put("URL", urlLeistung);
        
        pflegekraftHashMap.put("Name", MODELS.Pflegekraft.name());
        pflegekraftHashMap.put("SaveType", saveTypePflegekraft.name());
        pflegekraftHashMap.put("URL", urlPflegekraft);
        
        patientHashMap.put("Name", MODELS.Patient.name());
        patientHashMap.put("SaveType", saveTypePatient.name());
        patientHashMap.put("URL", urlPatient);
        
        configuration.add(leistungHashMap);
        configuration.add(pflegekraftHashMap);
        configuration.add(patientHashMap);
        
    }

    public ArrayList<HashMap<String, String>> getConfiguration() {
        return configuration;
    }
    public HashMap<String, String> getModel(MODELS model) throws ConfigurationException 
    {
    	for(HashMap<String, String> Hmodel : configuration)
    	{
    		if (Hmodel.get("Name").equals(model.name()))
    		{
    			return Hmodel;
    		}
    	}
    	throw new ConfigurationException("No Configuration for "+model.name());
    }
    public void setModel(MODELS model, SAVEDTYPE savedType, String url)
    {
    	HashMap<String,String> modelHashMap = new HashMap<>();
    	
    	modelHashMap.put("Name", model.name());
    	modelHashMap.put("SaveType", savedType.name());
    	modelHashMap.put("URL", url);
    	removeModel(model);
    	configuration.add(modelHashMap);
    	
    	
    }
    public String getName(MODELS model)
    {
    	return getModel(model).get("Name");
    }
    public String getSavedType(MODELS model)
    {
    	return getModel(model).get("SaveType");
    }
    public String getURL(MODELS model)
    {
    	return getModel(model).get("URL");
    }
    
    private void removeModel(MODELS model)
    {
    	for(int i = 0; i>configuration.size();i++)
    	{
    		if (configuration.get(i).get("Name").equals(model.name()))
    		{
    			configuration.remove(i);
    		}
    	}
    }
    
    




}
