package DataLayer.Services;

import Configuration.Models.Configuration;
import Configuration.Models.Configuration.SAVEDTYPE;
import DataLayer.DataAccessObjects.*;
import DataLayer.DataAccessObjects.File.Services.*;
import DataLayer.DataAccessObjects.db.DAOS.*;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;
import Models.*;
import Configuration.Models.Configuration.MODELS;
import DataLayer.DataAccessObjects.File.DAOS.*;
import Configuration.Exceptions.ConfigurationException;


import java.nio.file.*;
import java.sql.*;
import java.util.HashMap;

import javax.sql.PooledConnection;

public class DataLayerFactory {
	private Configuration configuration;
	private String location;
	
	public IDataLayer createDataLayer(Configuration configuration)
	{
		DataLayer output = new DataLayer();
		//liesst die configuration aus und führt mit ausgelesenen parametern die createDao funktion auf
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
		output.setDaoLeistung(createDao(MODELS.Pflegekraft, pflegekraftSType));
		}
		return (IDataLayer) output;
		
	}
	private IDao createDao(MODELS modelType, SAVEDTYPE DataSource)
	{
		switch(DataSource.name())
		{
		case "XML","CSV":
		{
			return createFileDao(modelType, DataSource);
		}
		case "SQLITE":
		{
			return createDbDao(modelType, DataSource);
		}
		default:
			throw new ConfigurationException(DataSource.name()+" DataSource does not exist");
		}
		
		
	}
	private IDao createDbDao(MODELS modelType, SAVEDTYPE DataSource)
	{
		switch(modelType.name())
		{
		case"Leistung":
		{
			LeistungDaoSqlite service = new LeistungDaoSqlite();
			return (IDao) service;
		}
		case"Patient":
		{
			PatientDaoSqlite service = new PatientDaoSqlite();
			return (IDao) service;
		}
		case"Pflegekraft":
		{
			PflegekraftDaoSqlite service = new PflegekraftDaoSqlite();
			return (IDao) service;
		}
		default:
		{
			throw new ConfigurationException("<"+modelType.name()+"> Model not Available");
		}
		}
	}
	private IDao createFileDao(MODELS modelType, SAVEDTYPE DataSource)
	{
		switch(modelType.name())
		{
		case"Leistung":
		{
			//setzt je nach File Type einen FilePersistenceService und den Pfad/URL aus der Config ein daoObject wird schlussendlich zurückgegeben
			LeistungDaoFile service = new LeistungDaoFile(getDataSourceFile(DataSource), Paths.get(configuration.getURL(modelType)));
			return  (IDao) service;
		}
		case"Patient":
		{
			//setzt je nach File Type einen FilePersistenceService und den Pfad/URL aus der Config ein daoObject wird schlussendlich zurückgegeben
			PatientDaoFile service = new PatientDaoFile(getDataSourceFile(DataSource), Paths.get(configuration.getURL(modelType)));
			return (IDao) service;
		}
		case"Pflegekraft":
		{
			//setzt je nach File Type einen FilePersistenceService und den Pfad/URL aus der Config ein daoObject wird schlussendlich zurückgegeben
			PflegekraftDaoFile service = new PflegekraftDaoFile(getDataSourceFile(DataSource), Paths.get(configuration.getURL(modelType)));

			return (IDao) service;
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
		
	
	

