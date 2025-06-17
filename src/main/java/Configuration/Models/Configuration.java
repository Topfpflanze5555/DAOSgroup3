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
    public void setModel(MODELS model, SAVEDTYPE savedType, String url)
    {
    	HashMap<String,String> modelHashMap = new HashMap<>();
    	
    	modelHashMap.put(Configuration.MODELPARAMS.Name.name(), model.name());
    	modelHashMap.put(Configuration.MODELPARAMS.SaveType.name(), savedType.name());
    	modelHashMap.put(Configuration.MODELPARAMS.URL.name(), url);
    	removeModel(model);
    	configuration.add(modelHashMap);
    	
    	
    }
    public String getName(MODELS model)
    {
    	return getModel(model).get(Configuration.MODELPARAMS.Name.name());
    }
    public String getSavedType(MODELS model)
    {
    	return getModel(model).get(Configuration.MODELPARAMS.SaveType.name());
    }
    public String getURL(MODELS model)
    {
    	return getModel(model).get(Configuration.MODELPARAMS.URL.name());
    }
    
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
