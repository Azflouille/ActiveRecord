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



}
