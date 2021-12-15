package testEtu;
import activeRecord.*;
import org.junit.*;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestSerie {

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
    }

    @Test
    public void testFindAll() throws SQLException {
        ArrayList<Serie> list = Serie.findAll();
        ArrayList<Serie> list2 = new ArrayList<>();
        Serie s1 = new Serie("Guimauve Throne", "Fantasy");
        s1.setID(1);
        list2.add(s1);
        Serie s2 = new Serie("Docteur Qui", "SF");
        s2.setID(2);
        list2.add(s2);
        Serie s3 = new Serie("Y fichier", "ParanoSF");
        s3.setID(3);
        list2.add(s3);
        Serie s4 = new Serie("Eastworld", "SF");
        s4.setID(4);
        list2.add(s4);
        Serie s5 = new Serie("Docteur Maison", "Mobilier Medical");
        s5.setID(5);
        list2.add(s5);
        assertEquals("Les listes devraient etre identiques", list, list2);
    }

    @Test
    public void testFindByID() throws SQLException {
        Serie s1 = Serie.findById(2);
        Serie s2 = new Serie("Docteur Qui", "SF");
        s2.setID(2);
        assertEquals("Les series devrait etre les memes", s1, s2);
    }

    @Test
    public void testFindByIdVoid() throws SQLException {
        Serie s1 = Serie.findById(8);
        assertEquals("Le résultat devrait etre null", s1, null);
    }

    @Test
    public void testFindByName() throws SQLException {
        ArrayList<Serie> s1 = Serie.findByName("Eastworld");
        ArrayList<Serie> s2 = new ArrayList<>();
        Serie s = new Serie("Eastworld", "SF");
        s.setID(4);
        s2.add(s);
        assertEquals("Les listes devraient etre identiques", s1, s2);
    }

    @Test
    public void testFindByNameVoid() throws SQLException {
        ArrayList<Serie> s1 = Serie.findByName("aled");
        ArrayList<Serie> s2 = new ArrayList<>();
        assertEquals("La liste devrait être vide", s1, s2);
    }

    @Test
    public void testFindByGenre() throws SQLException {
        ArrayList<Serie> s1 = Serie.findByGenre("SF");
        ArrayList<Serie> s2 = new ArrayList<>();
        Serie so = new Serie("Docteur Qui", "SF");
        so.setID(2);
        s2.add(so);
        Serie s = new Serie("Eastworld", "SF");
        s.setID(4);
        s2.add(s);
        assertEquals("Les listes devraient etre identiques", s1, s2);
    }

    @Test
    public void testFindByGenreVoid() throws SQLException {
        ArrayList<Serie> s1 = Serie.findByGenre("oskour");
        ArrayList<Serie> s2 = new ArrayList<>();
        assertEquals("La liste devrait etre vide", s1, s2);
    }

    @After
    public void testDeleteTable() throws SQLException {
        Serie.deleteTable();
    }

}