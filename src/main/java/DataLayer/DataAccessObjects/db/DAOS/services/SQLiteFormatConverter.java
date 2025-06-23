package DataLayer.DataAccessObjects.db.DAOS.services;

public class SQLiteFormatConverter {
    public static double formatIntToDouble(int num) {
        return (double) num / 100;
    }
}
