package DataLayer.Services;

import Configuration.Models.Configuration;
import Configuration.Models.Configuration.SAVEDTYPE;
import DataLayer.DataAccessObjects.*;
import DataLayer.DataAccessObjects.File.Services.*;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;
import Configuration.Models.Configuration.MODELS;
import DataLayer.DataAccessObjects.File.DAOS.*;
import Configuration.Exceptions.ConfigurationException;
import java.nio.file.*;

public class DataLayerFactory {
	private Configuration configuration;
	public DataLayerFactory(Configuration configuration)
	{
		this.configuration = configuration;
	}
	
	public IDataLayer createDataLayer()
	{
		DataLayer output = new DataLayer();
		//liest die configuration aus und führt mit ausgelesenen parametern die createDao funktion auf
		//für jeden datenTypen
		{
		SAVEDTYPE leistungSType = SAVEDTYPE.valueOf(configuration.getSavedType(MODELS.Leistung));
		output.setDaoLeistung(createDao(MODELS.Leistung, leistungSType));
		}
		{
		
		SAVEDTYPE patientSType = SAVEDTYPE.valueOf(configuration.getSavedType(MODELS.Patient));
		output.setDaoPatient(createDao(MODELS.Patient, patientSType));
		}
		{
		SAVEDTYPE pflegekraftSType = SAVEDTYPE.valueOf(configuration.getSavedType(MODELS.Pflegekraft));
		output.setDaoPflegekraft(createDao(MODELS.Pflegekraft, pflegekraftSType));
		}
		return (IDataLayer) output;
		
	}
	private <T,ID> IDao<T,ID> createDao(MODELS modelType, SAVEDTYPE DataSource)
	{
        return switch (DataSource.name()) {
            case "XML", "CSV" -> createFileDao(modelType, DataSource);
            case "SQLITE" -> createDbDao(modelType, DataSource);
            default -> throw new ConfigurationException(DataSource.name() + " DataSource does not exist");
        };
		
		
	}

	private <T,ID> IDao<T,ID> createDbDao(MODELS modelType, SAVEDTYPE DataSource)
	{
        switch (modelType.name()) {
            case "Leistung" -> {
                
                return (IDao<T,ID>) getDataSourceDb(DataSource);
            }
            case "Patient" -> {

                return (IDao<T,ID>) getDataSourceDb(DataSource);
            }
            case "Pflegekraft" -> {
				return (IDao<T,ID>) getDataSourceDb(DataSource);
			}
			default -> {throw new ConfigurationException("<" + modelType.name() + "> Model not Available");}

		}
        }


	/**
	 *
	 * @param modelType enum which corresponds to a model type
	 * @param DataSource enum which corresponds to a persistence service
	 * @return Dao object for the specified configuration
	 * @param <T> Type of the Model
	 * @param <ID> Type of the Key variable of the model
	 */
	@SuppressWarnings("unchecked")
	private <T,ID> IDao<T,ID>  createFileDao(MODELS modelType, SAVEDTYPE DataSource)
	{

		switch(modelType.name())
		{
		case"Leistung":
		{
			//setzt je nach File Type einen FilePersistenceService und den Pfad/URL aus der Config ein daoObject wird schlussendlich zurückgegeben


			 return  (IDao<T, ID>) new LeistungDaoFile(getDataSourceFile(DataSource), Paths.get(configuration.getURL(modelType)));
			
		}
		case"Patient":
		{
			//setzt je nach File Type einen FilePersistenceService und den Pfad/URL aus der Config ein daoObject wird schlussendlich zurückgegeben
			return (IDao<T,ID>) new PatientDaoFile(getDataSourceFile(DataSource), Paths.get(configuration.getURL(modelType)));
			
		}
		case"Pflegekraft":
		{
			//setzt je nach File Type einen FilePersistenceService und den Pfad/URL aus der Config ein daoObject wird schlussendlich zurückgegeben
			return (IDao<T,ID>) new PflegekraftDaoFile(getDataSourceFile(DataSource), Paths.get(configuration.getURL(modelType)));

			
		}
		default:
		{
			throw new ConfigurationException("<"+modelType.name()+"> Model not Available");
		}
		}
	}
	
	
	private <T> IFilePersistenceService<T> getDataSourceFile(SAVEDTYPE DataSource)
	{
		
		switch(DataSource.name())
		{
		case"XML":
		{
			return new FilePersistenceServiceXml<T>();
		}
		case"CSV":
		{
			return new FilePersistenceServiceCsv<T>(',');
			
		}
		}
		throw new ConfigurationException(DataSource.name()+" DataSource does not exist as File");
		
	}
	private ConnectionManager getDataSourceDb(SAVEDTYPE DataSource)
	{
		
		switch(DataSource.name())
		{
			case"SQLITE":
		{
			return new ConnectionManagerSqlite();
		}
		}
		throw new ConfigurationException(DataSource.name()+" DataSource does not exist as db");
		
	}
}

	
	

