package pt.isel.ls.dbconnection;

import org.junit.*;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by fabio on 12-Mar-16.
 */
public class ActionsTest {

    @After
    public void undoInsertTest() throws SQLException {
        Actions actions = new Actions();
        actions.deleteAluno(666);
        actions.deleteAluno(555);
        actions.deleteAluno(000);
    }

    @Before
    public void insertAlunoToDelete() throws SQLException {
        Actions actions = new Actions();
        actions.insertAlunoInDB(555, "Aluno");
        actions.insertAlunoInDB(000, "AlunoToUpdate");
    }

    @Test
    public void selectValidAluno() throws SQLException {

        //assertEquals(39239,new Actions().selectAluno(39329).Numero);
        assertEquals("Fabio Parreirinha",new Actions().selectAluno(39329).Nome);
        assertEquals("Tede Morgado",new Actions().selectAluno(38225).Nome);
        assertEquals("Daniel Cabral",new Actions().selectAluno(40569).Nome);
    }

    @Test
    public void insertTest() throws SQLException {
        Actions actions = new Actions();
        actions.insertAlunoInDB(666, "joaquim");
        assertEquals("joaquim",new Actions().selectAluno(666).Nome);
    }

    @Test
    public void deleteAlunoTest() throws SQLException {
        Actions actions = new Actions();
        actions.deleteAluno(555);
        assertEquals(null,new Actions().selectAluno(555));
    }

    @Test
    public void updateAlunoTest() throws SQLException {
        Actions actions = new Actions();
        actions.updateStudentName(000,"Novo Nome");
        assertEquals("Novo Nome",new Actions().selectAluno(000).Nome);
    }
}
