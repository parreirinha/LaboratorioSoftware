package pt.isel.ls.database.access;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.mapping.CommandMapper;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;


import static org.junit.Assert.assertEquals;

public class PostMovieTest {



    private GetMovie getMovie = new GetMovie();
    private DataCreationTests dataCreationTests;
    private Connection connection;
    private Movie movie;
    private Command command;


    @Test
    public void postOneMovie() throws SQLException {



    }


    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }

    @Before
    private void initValues() throws SQLServerException {


    }
}
