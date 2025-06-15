package DataLayer.Services;

import Configuration.Services.ConfigurationPersistenceService;

public class DataLayerManager {
	private static DataLayerManager instance = null;
	private IDataLayer dataLayer;
	
	private DataLayerManager()
	{
		ConfigurationPersistenceService cps = ConfigurationPersistenceService.getInstance();
		DataLayerFactory dlf = new DataLayerFactory(cps.getConfiguration());
		dataLayer = dlf.createDataLayer();
	}
	public static DataLayerManager getInstance()
	{
		if (instance == null)
		{
			instance = new DataLayerManager();
		}
		return instance;
	}
}
