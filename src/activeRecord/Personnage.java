package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Personnage {

    private String nom;
    private int id;
    private int id_serie;

    public Personnage(String n, int id_s) {
        this.nom = n;
        this.id = -1;
        this.id_serie = id_s;
    }

    private Personnage(String n, int i, int id_s) {
        this.nom = n;
        this.id = i;
        this.id_serie = id_s;
    }

    public Personnage findById(int i) throws SQLException {
        Personnage perso = null;
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("SELECT * FROM PERSONNAGE WHERE ID = ?");
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setInt(1, i);
        ResultSet resultset = stmt.executeQuery();
        while (resultset.next()) {
            perso = new Personnage(resultset.getString("NOM"), i, resultset.getInt("id_serie"));
        }
        return perso;
    }

    public Serie getSerie() throws SQLException {
        return Serie.findById(this.id_serie);
    }

}
