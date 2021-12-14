package testEtu;
import activeRecord.*;
import org.junit.*;
import java.sql.*;
import static org.junit.Assert.*;

public class DBConnectionTest {
    @Test
    public void test() throws SQLException {
        DBConnection dbaled = DBConnection.getInstance();
        DBConnection dboskour = DBConnection.getInstance();

        Connection connect1 = dbaled.getConnection();
        Connection connect2 = dboskour.getConnection();
        assertEquals("Les connections devrait etre identitques", connect1, connect2);
    }

    @Test
    public void voidTestChangement() throws SQLException {
        DBConnection dbaled = DBConnection.getInstance();
        dbaled.setNomDB("bouh");
        DBConnection dboskour = DBConnection.getInstance();
        Connection connect1 = dbaled.getConnection();
        Connection connect2 = dboskour.getConnection();
        assertNotEquals("Les connections devrait etre differentes", connect1, connect2);
    }

}