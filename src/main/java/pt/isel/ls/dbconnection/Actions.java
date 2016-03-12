package pt.isel.ls.dbconnection;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by fabio on 10-Mar-16.
 */
public class Actions {

    private Connection connection;
    private PreparedStatement preparedStatement = null;

    /**
     * returns the a instance of type Aluno with the number in the paramether of the method
     * @param number of the student we want to return
     * @return  object Aluno
     * @throws SQLException
     */
    public  Aluno selectAluno(int number) throws SQLException {
        //TODO método para melhorar, nao consegui realizar de forma correcta
        ArrayList<Aluno> container = (ArrayList<Aluno>) getAllStudents();
        Aluno item;
        for (Aluno a: container) {
            if (a.Numero == number){
                item = a;
                return item;
            }
        }
        return null;
    }

    /**
     * returns a collection of all "Alunos"
     * @return
     * @throws SQLException
     */
    public Collection<Aluno> getAllStudents() throws SQLException {

        String statementQuery = "select Nome, Numero from Aluno";
        Aluno item;
        ArrayList<Aluno> container = new ArrayList<Aluno>();
        connection = new ConnectionFactory().connectionFactory();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(statementQuery);


        while (rs.next()) {
            item = new Aluno();
            item.Nome = rs.getString(1);
            item.Numero = rs.getInt(2);
            container.add(item);
        }
        connection.close();
        return container;
    }

    /**
     * insert new Aluno in the DB
     * @param num number of the "Aluno"
     * @param nom Name of the "Aluno"
     * @throws SQLException
     */
    public void insertAlunoInDB(int num, String nom) throws SQLException {

        String query = "insert into Aluno (Nome, Numero) " + "values(?,?)";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nom);
        preparedStatement.setInt(2, num);
        preparedStatement.executeUpdate();
        connection.commit();
        if (connection != null)
            connection.close();
    }

    /**
     * update the name of the "Aluno" with the number inserted in parameter
     * @param num       number of the Aluno
     * @param newName   new name
     * @throws SQLException
     */
    public void updateStudentName(int num, String newName) throws SQLException {

        String query = "update Aluno set Nome = ? where (Numero = ?)";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, num);
        preparedStatement.executeUpdate();
        connection.commit();
        if (connection != null)
            connection.close();
    }

    /**
     * delete the Aluno with the number inserted in the paramenter
     * @param number
     * @throws SQLException
     */
    public void deleteAluno(int number) throws SQLException {
        String query = "delete from Aluno where (Numero = ?)";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, number);
        preparedStatement.executeUpdate();
        connection.commit();
        if (connection != null)
            connection.close();
    }

}
