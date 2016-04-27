package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Auxiliar class that contains auxiliar methods that are used in more than one linecommand
 */
public class AccessUtils {

    public static String getColumnName(int rating) {

        String[] star = {"OneStar", "TwoStar", "TreeStar", "FourStar", "FiveStar"};
        return star[rating - 1];
    }

    /**
     * set values in the preparement statement
     *
     * @param ps   PreparementStatement
     * @param vals values to set
     * @throws SQLException
     */
    public static void setValuesOnPreparedStatement(PreparedStatement ps, Object... vals) throws SQLException {
        //TODO só suporta strings e int tornar mais abrangente! mas abrangente em quê?!
        int i = 0;
        for (; i < vals.length; i++) {
            if (vals[i] instanceof String)
                ps.setString(i + 1, (String) vals[i]);
            else {
                ps.setInt(i + 1, (Integer) vals[i]);
            }
        }
    }

    public static int[] returnArrayStarsGivenAResultSet(ResultSet rs) throws SQLException {
        int[] stars = new int[5];
        stars[0] = rs.getInt(4);
        stars[1] = rs.getInt(5);
        stars[2] = rs.getInt(6);
        stars[3] = rs.getInt(7);
        stars[4] = rs.getInt(8);
        return stars;
    }

    public static String ratingAverageFormula() {
        return
                "CONVERT(DECIMAL(4,3), " +
                        "((Movie.OneStar + Movie.TwoStar*2 + Movie.TreeStar * 3 + Movie.FourStar * 4 " +
                        "+ Movie.FiveStar * 5)/" +
                        "cast(((Movie.OneStar + Movie.TwoStar + Movie.TreeStar + Movie.FourStar + " +
                        "Movie.FiveStar)) " +
                        "AS DECIMAL (4,0)))) as rating ";
    }

    public static PreparedStatement preparedStatementWithPaging(Connection connection, String query, Command command)
            throws SQLException {
        PreparedStatement ps;
        if (pagingVerification(command)) {
            int skip = command.getParams().getParamInt("skip") + 1;
            int top = skip + command.getParams().getParamInt("top") - 1;
            String pagingQuery =
                "select * from ( " +
                        query + " ) as res \n " +
                "where RowNumber BETWEEN ? AND ? " +
                setOrderByClause(command);
            ps = connection.prepareStatement(pagingQuery);
            setValuesOnPreparedStatement(ps, skip, top);
            return ps;
        }
        return (ps = connection.prepareStatement(query));
    }

    protected static boolean pagingVerification(Command command) {
        //todo nesta verificação se nao existir skip e top lança excepção
        Integer i1 = command.getParams().getParamInt("skip");
        Integer i2 = command.getParams().getParamInt("top");
        return (i1 == null) || (i2 == null) ? false : true;
    }



    public static String setOrderByClause(Command command) {
        if (command.getParams().getParamString("sortBy") != null) {
            initSortDecoderHashMap();
            return sortDecoder.get(command.getParams().getParamString("sortBy"));
        }
        return "order by MovieID ASC";
    }

    private static HashMap<String, String> sortDecoder;

    private static void initSortDecoderHashMap(){
        sortDecoder = new HashMap<String, String>();
        sortDecoder.put("addedData","order by MovieID ASC" );
        sortDecoder.put("addedDataDesc", "order by MovieID DESC");
        sortDecoder.put("year", "order by MovieRelease ASC");
        sortDecoder.put("yearDesc", "order by MovieRelease DESC");
        sortDecoder.put("title", "order by MovieName ASC");
        sortDecoder.put("titleDesc","order by MovieName DEC");
        sortDecoder.put("rating", "order by rating ASC");
        sortDecoder.put("ratingDesc", "order by rating DESC");
    }

    public static String getClumnRowCountString(Command command){
        return "ROW_NUMBER() OVER (" +setOrderByClause(command)+ ") AS RowNumber";
    }

    public static String getRatingColumnFormula(){
        return "CONVERT(DECIMAL(4,3), " +
                "((Movie.OneStar + Movie.TwoStar*2 + Movie.TreeStar * 3 + Movie.FourStar * 4 + Movie.FiveStar * 5)/" +
                "cast(((Movie.OneStar + Movie.TwoStar + Movie.TreeStar + Movie.FourStar + Movie.FiveStar)) AS DECIMAL (4,0)))) as rating";
    }

}