package DataLayer.Services;

import DataLayer.DataAccessObjects.IDao;
import Models.*;

public interface IDataLayer {
	IDao<Leistung, String> getDaoLeistung();
	IDao<Patient, Long> getDaoPatient();
	IDao<Pflegekraft, Long> getDaoPflegekraft();
}
