package activeRecord;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

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

    public static ArrayList<Serie> findAll() throws SQLException {
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

    public static Serie findById(int i) throws SQLException {
        Serie serie = null;
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("SELECT * FROM SERIE WHERE ID = ?");
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setInt(1, i);
        ResultSet resultset = stmt.executeQuery();
        while (resultset.next()) {
            serie = new Serie(resultset.getString("nom"), resultset.getString("genre"));
            int ID = resultset.getInt("ID");
            serie.setID(ID);
        }
        return serie;
    }

    public static ArrayList<Serie> findByName(String search) throws SQLException {
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

    public static ArrayList<Serie> findByGenre(String genre) throws SQLException {
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

    public void delete() throws SQLException {
        if (this.id != -1) {
            Connection connect = DBConnection.getInstance().getConnection();
            String query = ("DELETE FROM SERIE WHERE ID = ?");
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, this.id);
            stmt.executeUpdate();
            this.id = -1;
        }
    }

    public void save() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        if (this.id == -1) {
            //saveNew
            String query = ("SELECT COUNT(ID) FROM SERIE");
            Statement stmt = connect.createStatement();
            ResultSet resultset = stmt.executeQuery(query);
            resultset.next();
            int idouille = resultset.getInt(1);
            query = ("INSERT INTO SERIE VALUES (?,?,?)");
            PreparedStatement stmtmt = connect.prepareStatement(query);
            stmtmt.setInt(1, idouille+1);
            stmtmt.setString(2, this.nom);
            stmtmt.setString(3, this.genre);
            stmtmt.executeUpdate();
            this.id = idouille+1;
        }
        else {
            //update
            String query = ("UPDATE SERIE SET NOM = ?, GENRE = ? WHERE ID = ?");
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setString(1, this.nom);
            stmt.setString(2, this.genre);
            stmt.setInt(3, this.id);
            stmt.executeUpdate();
        }
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getGenre() {
        return this.nom;
    }

    public void setNom(String n) {
        this.nom = n;
    }

    public void setGenre(String g) {
        this.genre = g;
    }

}
