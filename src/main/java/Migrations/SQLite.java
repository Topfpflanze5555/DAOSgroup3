package Migrations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:patients.db";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

            migrate(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void migrate(Connection conn) throws SQLException {
        String patienten = "CREATE TABLE IF NOT EXISTS patienten (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    vorname TEXT NOT NULL,\n" +
                "    nachname TEXT NOT NULL,\n" +
                "    geburtsdatum REAL NOT NULL,\n" +
                "    pflegegrad INTEGER,\n" +
                "    zimmer INTEGER,\n" +
                "    vermoegen INTEGER\n" + // SQLite doesnt support decimals. This means we just treat the last two digits of this number as decimals. So, 1.00 would be 100. Not great, but there isn't a better way of doing it.
                ")";

        conn.createStatement().execute(patienten);


        String pflegekraft = "CREATE TABLE IF NOT EXISTS pflegekraft (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    vorname TEXT NOT NULL,\n" +
                "    nachname TEXT NOT NULL,\n" +
                "    geburtsdatum REAL NOT NULL,\n" +
                "    pflegegrad INTEGER,\n" +
                "    zimmer INTEGER,\n" +
                "    vermoegen INTEGER\n" +
                ")";

        conn.createStatement().execute(pflegekraft);


        String leistung = "CREATE TABLE IF NOT EXISTS leistung (\n" +
                "    lkNr TEXT PRIMARY KEY,\n" + // String primary keys arent great, but this is in the spec so we gotta do it
                "    bezeichnung TEXT,\n" +
                "    beschreibung TEXT\n" +
                ")";

        conn.createStatement().execute(leistung);
    }
}
