package DataLayer.Services;
import DataLayer.DataAccessObjects.IDao;
import Models.*;
public class DataLayer implements  IDataLayer{
	private IDao<Leistung, String> daoLeistung;
	private IDao<Patient, Long> daoPatient;
	private IDao<Pflegekraft, Long> daoPflegekraft;
	
	protected void setDaoLeistung(IDao<Leistung, String> daoLeistung)
	{
		this.daoLeistung = daoLeistung;
	}
	protected void setDaoPatient(IDao<Patient, Long> daoPatient)
	{
		this.daoPatient = daoPatient;
	}
	protected void setDaoPflegekraft(IDao<Pflegekraft, Long> daoPflegekraft)
	{
		this.daoPflegekraft = daoPflegekraft;
	}

	@Override
	public String toString() {
		return "DataLayer [daoLeistung=" + daoLeistung + ", daoPatient="+daoPatient+", daoPflegekraft="+daoPflegekraft+"]";
	}

	@Override
	public IDao<Leistung, String> getDaoLeistung() {
		return daoLeistung;
	}

	@Override
	public IDao<Patient, Long> getDaoPatient() {
		return daoPatient;
	}

	@Override
	public IDao<Pflegekraft, Long> getDaoPflegekraft() {
		return daoPflegekraft;
	}
}
