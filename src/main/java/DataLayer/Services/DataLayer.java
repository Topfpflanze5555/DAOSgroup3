package DataLayer.Services;
import DataLayer.DataAccessObjects.IDao;
import Models.*;
public class DataLayer {
	private IDao<Leistung, String> daoLeistung;
	private IDao<Patient, Long> daoPatient;
	private IDao<Pflegekraft, String> daoPflegekraft;
	
	protected void setDaoLeistung(IDao<Leistung, String> daoLeistung)
	{
		this.daoLeistung = daoLeistung;
	}
	protected void setDaoPatient(IDao<Patient, Long> daoPatient)
	{
		this.daoPatient = daoPatient;
	}
	protected void setDaoPflegekraft(IDao<Pflegekraft, String> daoPflegekraft)
	{
		this.daoPflegekraft = daoPflegekraft;
	}
}
