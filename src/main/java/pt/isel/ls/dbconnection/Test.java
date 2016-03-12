package pt.isel.ls.dbconnection;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by fabio on 10-Mar-16.
 */
public class Test {


    //private static Actions action = new Actions();

    public static void main(String[] args) throws SQLException {

        Actions action = new Actions();


        //action.insertAlunoInDB(6969, "andré");
        //action.updateStudentName(5339, "joao Miguel");
        //action.deleteAluno(6969);
        Collection<Aluno> students = action.getAllStudents();
        for (Aluno a:students) {
            System.out.println(a.Nome + "  " + a.Numero);
        }

        Aluno a = action.selectAluno(39329);
        System.out.println("\n" + a.Nome + "\n" + a.Numero);

    }

}

