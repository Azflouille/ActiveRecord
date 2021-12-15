package testEtu;
import activeRecord.*;
import org.junit.*;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestPersonnage {

    @Before
    public void testCreateTable() throws SQLException{
        Serie.createTable();
        Serie s1 = new Serie("Guimauve Throne", "Fantasy");
        s1.save();
        Serie s2 = new Serie("Docteur Qui", "SF");
        s2.save();
        Serie s3 = new Serie("Y fichier", "ParanoSF");
        s3.save();
        Serie s4 = new Serie("Eastworld", "SF");
        s4.save();
        Serie s5 = new Serie("Docteur Maison", "Mobilier Medical");
        s5.save();
        Personnage.createTable();
        Personnage p1 = new Personnage("Jean Neige", s1);
        p1.save();
        Personnage p2 = new Personnage("Doigts boudinnes", s1);
        p2.save();
        Personnage p3 = new Personnage("Thirion", s1);
        p3.save();
        Personnage p4 = new Personnage("Le docteur", s2);
        p4.save();
        Personnage p5 = new Personnage("Le 2nd docteur", s2);
        p5.save();
        Personnage p6 = new Personnage("Le 3nd docteur", s2);
        p6.save();
        Personnage p7 = new Personnage("Renard Meule D''or'", s3);
        p7.save();
        Personnage p8 = new Personnage("Dana Scully", s3);
        p8.save();
        Personnage p9 = new Personnage("Dolores", s4);
        p9.save();
        Personnage p10 = new Personnage("Gregory Maison", s5);
        p10.save();
        Personnage p11 = new Personnage("John Appartement", s5);
        p11.save();
        Personnage p12 = new Personnage("Sylvie F2bis", s5);
        p12.save();
    }

    @Test
    public void testFindByID() throws SQLException {
        Personnage p1 = Personnage.findById(2);
        Serie s1 = new Serie("Guimauve Throne", "Fantasy");
        s1.setID(1);
        Personnage p2 = new Personnage("Doigts boudinnes", s1);
        p2.setID(2);
        assertEquals("Les Personnages devraient etre les memes", p1, p2);
    }

    @Test
    public void testFindByIDVoid() throws SQLException {
        Personnage p1 = Personnage.findById(28);
        assertEquals("Le resultat devrait être null", p1, null);
    }

    @Test
    public void testFindBySerie() throws SQLException, SerieAbsenteException {
        Serie s1 = new Serie("Guimauve Throne", "Fantasy");
        s1.setID(1);
        ArrayList<Personnage> p1 = Personnage.findBySerie(s1);
        ArrayList<Personnage> p = new ArrayList<>();
        Personnage p2 = new Personnage("Jean Neige", s1);
        p2.setID(1);
        p.add(p2);
        Personnage p3 = new Personnage("Doigts boudinnes", s1);
        p3.setID(2);
        p.add(p3);
        Personnage p4 = new Personnage("Thirion", s1);
        p4.setID(3);
        p.add(p4);
        assertEquals("Les Personnages devraient etre les memes", p1, p);
    }

    @Test
    public void testFindByGenre() throws SQLException, SerieAbsenteException {
        Serie s1 = new Serie("Guimauve Throne", "Fantasy");
        s1.setID(1);
        ArrayList<Personnage> p1 = Personnage.findByGenre("Fantasy");
        ArrayList<Personnage> p = new ArrayList<>();
        Personnage p4 = new Personnage("Thirion", s1);
        p4.setID(3);
        p.add(p4);
        Personnage p3 = new Personnage("Doigts boudinnes", s1);
        p3.setID(2);
        p.add(p3);
        Personnage p2 = new Personnage("Jean Neige", s1);
        p2.setID(1);
        p.add(p2);
        assertEquals("Les Personnages devraient etre les memes", p1, p);
    }

    @Test
    public void testFindByGenreVoid() throws SQLException, SerieAbsenteException {
        Serie s1 = new Serie("Guimauve Throne", "Fantasy");
        s1.setID(1);
        ArrayList<Personnage> p1 = Personnage.findByGenre("DenisBrackmahr");
        ArrayList<Personnage> p = new ArrayList<>();
        assertEquals("Les Personnages devraient etre les memes", p1, p);
    }

    @After
    public void testDeleteTable() throws SQLException {
        Personnage.deleteTable();
        Serie.deleteTable();
    }

}