package activeRecord;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    // variables a modifier en fonction de la base
    private String userName = "root";
    private String password = "";
    private String serverName = "localhost";
    //Attention, sous MAMP, le port est 8889
    private String portNumber = "3306";
    private String tableName = "Serie";
    // iL faut une base nommee testSerie !
    private static String dbName = "testSerie";
    private Connection connect;
    private static DBConnection instance;

    private DBConnection() throws SQLException {
        // creation de la connection
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;
        connect = DriverManager.getConnection(urlDB, connectionProps);
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connect;
    }

    public static void setNomDB(String nomDB) {
        dbName = nomDB;
        instance = null;
    }

}
