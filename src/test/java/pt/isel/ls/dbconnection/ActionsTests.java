package pt.isel.ls.dbconnection;
import org.junit.Test;
import pt.isel.ls.dbconnection.Actions;
import pt.isel.ls.dbconnection.Aluno;
import pt.isel.ls.dbconnection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/**
 * Class used to test Actions class methods:
 * insertAlunoInDB, updateStudentName and deleteAluno.
 *
 */
public class ActionsTests {
    private Actions action = new Actions();
    private Aluno a = new Aluno("Antonio", 12345);
    private Connection connection;

    //helper boolean method that verifies if a student exists in the database students table
    private boolean existsInTable(ResultSet rs, Aluno aluno) throws SQLException {
        while(rs.next())
        {
            if(rs.getString(1).equals(aluno.Nome) && rs.getInt(2) == aluno.Numero )
                return true;
        }
        return false;
    }

    //insertion test
    @Test
    public void insertIntoTableTest() throws SQLException {
        action.insertAlunoInDB(a.Numero, a.Nome);
        connection= new ConnectionFactory().connectionFactory();
        ResultSet rs = connection.prepareStatement("SELECT * FROM Students").executeQuery();
        connection.close();
        boolean isInserted = existsInTable(rs, a);
        rs.close();
        assertTrue(isInserted);
    }

    //update test
    @Test
    public void updateTableTest() throws SQLException {
        a.Nome = "Antonio Manuel";
        action.updateStudentName(a.Numero, a.Nome);
        connection= new ConnectionFactory().connectionFactory();
        ResultSet rs = connection.prepareStatement("SELECT * FROM Students").executeQuery();
        connection.close();
        boolean isUpdated = existsInTable(rs, a);
        rs.close();
        assertTrue(isUpdated);
    }

    //delete test
    @Test
    public void deleteFromTableTest() throws SQLException {
        action.deleteAluno(a.Numero);
        connection= new ConnectionFactory().connectionFactory();
        ResultSet rs = connection.prepareStatement("SELECT * FROM Students").executeQuery();
        connection.close();
        boolean isDeleted = !existsInTable(rs, a);
        rs.close();
        assertTrue(isDeleted);
    }

}
