package activeRecord;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Serie {

    private int id;
    private String nom;
    private String genre;

    public Serie (String n, String g) {
        this.id = -1;
        this.nom = n;
        this.genre = g;
    }

    public void setID (int i) {
        this.id = i;
    }

    public ArrayList<Serie> findAll() throws SQLException {
        ArrayList series = new ArrayList<Serie>();
        Connection connect = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM SERIE";
        Statement stmt = connect.createStatement();
        ResultSet resultset = stmt.executeQuery(query);
        while (resultset.next()) {
            Serie serie = new Serie(resultset.getString("nom"), resultset.getString("genre"));
            int ID = resultset.getInt("ID");
            serie.setID(ID);
            series.add(serie);
        }
        return series;
    }

    public Serie findById(int i) throws SQLException {
        Serie serie = null;
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("SELECT * FROM SERIE WHERE ID = ?");
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setInt(1, i);
        ResultSet resultset = stmt.executeQuery();
        while (resultset.next()) {
            new Serie(resultset.getString("nom"), resultset.getString("genre"));
            int ID = resultset.getInt("ID");
            serie.setID(ID);
        }
        return serie;
    }

    public ArrayList<Serie> findByName(String search) throws SQLException {
        ArrayList series = new ArrayList<Serie>();
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("SELECT * FROM SERIE WHERE NOM LIKE ?");
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setString(1, "%" + search + "%");
        ResultSet resultset = stmt.executeQuery();
        while (resultset.next()) {
            Serie serie = new Serie(resultset.getString("nom"), resultset.getString("genre"));
            int ID = resultset.getInt("ID");
            serie.setID(ID);
            series.add(serie);
        }
        return series;
    }

    public ArrayList<Serie> findByGenre(String genre) throws SQLException {
        ArrayList series = new ArrayList<Serie>();
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("SELECT * FROM SERIES WHERE GENRE = ?");
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setString(1, genre);
        ResultSet resultset = stmt.executeQuery();
        while (resultset.next()) {
            Serie serie = new Serie(resultset.getString("nom"), resultset.getString("genre"));
            int ID = resultset.getInt("ID");
            serie.setID(ID);
            series.add(serie);
        }
        return series;
    }

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("CREATE TABLE `serie` (\n" +
                "  `ID` int(11) NOT NULL,\n" +
                "  `NOM` varchar(40) NOT NULL,\n" +
                "  `GENRE` varchar(40) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(query);
    }

    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("DROP TABLE SERIE");
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(query);
    }

    

}
