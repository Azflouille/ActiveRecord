package activeRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Personnage {

    private int id;
    private String nom;
    private int id_serie;

    public Personnage(String n, Serie s) {
        this.nom = n;
        this.id = -1;
        this.id_serie = s.getId();
    }

    private Personnage(String n, int i, int id_s) {
        this.nom = n;
        this.id = i;
        this.id_serie = id_s;
    }

    public static Personnage findById(int i) throws SQLException {
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

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("CREATE TABLE `personnage` (\n" +
                "  `ID` int(11) NOT NULL,\n" +
                "  `NOM` varchar(40) NOT NULL,\n" +
                "  `ID_SERIE` int(11) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(query);
    }

    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("DROP TABLE PERSONNAGE");
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(query);
    }

    public void delete() throws SQLException {
        if (this.id != -1) {
            Connection connect = DBConnection.getInstance().getConnection();
            String query = ("DELETE FROM PERSONNAGE WHERE ID = ?");
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
            String query = ("SELECT COUNT(ID) FROM PERSONNAGE");
            Statement stmt = connect.createStatement();
            ResultSet resultset = stmt.executeQuery(query);
            resultset.next();
            int idouille = resultset.getInt(1);
            query = ("INSERT INTO PERSONNAGE VALUES (?,?,?)");
            PreparedStatement stmtmt = connect.prepareStatement(query);
            stmtmt.setInt(1, idouille+1);
            stmtmt.setString(2, this.nom);
            stmtmt.setInt(3, this.id_serie);
            stmtmt.executeUpdate();
            this.id = idouille+1;
        }
        else {
            //update
            String query = ("UPDATE PERSONNAGE SET ID = ?, NOM = ?, ID_SERIE = ?");
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, this.id);
            stmt.setString(2, this.nom);
            stmt.setInt(3, this.id_serie);
            stmt.executeUpdate();
        }
    }

    public static ArrayList<Personnage> findBySerie(Serie s) throws SQLException, SerieAbsenteException {
        ArrayList persos = new ArrayList<Personnage>();
        Connection connect = DBConnection.getInstance().getConnection();
        String query = ("SELECT * FROM PERSONNAGE WHERE ID_SERIE = ?");
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setInt(1, s.getId());
        ResultSet resultset = stmt.executeQuery();
        int aled = 0;
        while (resultset.next()) {
            aled++;
            persos.add(new Personnage(resultset.getString("nom"), resultset.getInt("id"),s.getId()));
        }
        if (persos.size() == 0) {
            throw new SerieAbsenteException();
        }
        return persos;
    }

    public static ArrayList<Personnage> findByGenre(String g) throws SQLException, SerieAbsenteException {
        ArrayList persos = new ArrayList<Personnage>();
        ArrayList series = Serie.findByGenre(g);
        int compteur = series.size();
        while (compteur > 0) {
            compteur--;
            Serie s =(Serie) series.get(compteur);
            ArrayList<Personnage> tmp = Personnage.findBySerie(s);
            int compteur2 = tmp.size();
            while (compteur2 > 0) {
                compteur2--;
                Personnage p = tmp.get(compteur2);
                persos.add(p);
            }
        }
        return persos;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnage that = (Personnage) o;
        return id == that.id && id_serie == that.id_serie && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, id_serie);
    }

    public void setID(int i) {
        this.id = i;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String n) {
        this.nom = n;
    }

}
