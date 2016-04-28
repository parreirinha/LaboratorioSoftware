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

    protected static String getColumnName(int rating) {

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
    protected static void setValuesOnPreparedStatement(PreparedStatement ps, Object... vals) throws SQLException {
        //TODO só suporta strings e int tornar mais abrangente! mas abrangente em quê?!
        int i = 0;
        for (; i < vals.length; i++) {
            if (vals[i] instanceof String)
                ps.setString(i + 1, (String) vals[i]);
            else {
                if(i!=-1)
                ps.setInt(i + 1, (Integer) vals[i]);
            }
        }
    }

    protected static int[] returnArrayStarsGivenAResultSet(ResultSet rs) throws SQLException {
        int[] stars = new int[5];
        stars[0] = rs.getInt(4);
        stars[1] = rs.getInt(5);
        stars[2] = rs.getInt(6);
        stars[3] = rs.getInt(7);
        stars[4] = rs.getInt(8);
        return stars;
    }


    protected static String concatenateQuearyIfExistsPaging(String query, Command command, String orderBy)
            throws SQLException {

        PreparedStatement ps;
        if (pagingVerification(command)) {
            String pagingQuery =
                "select * from ( " + query + " ) as res \n " +
                "where RowNumber BETWEEN ? AND ? " + setOrderByClause(command, orderBy );
            return pagingQuery;
        }
        return query;
    }

    protected static boolean pagingVerification(Command command) {
        Integer i1 = command.getParams().getParamInt("skip");
        Integer i2 = command.getParams().getParamInt("top");
        return (i1 < 0) || (i2 < 0) ? false : true;
    }



    protected static String setOrderByClause(Command command, String orderBy) {
        if (orderBy != null) {
            return "order by " + orderBy;
        }
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
        sortDecoder.put("rating", "order by Average ASC");
        sortDecoder.put("ratingDesc", "order by Average DESC");
    }

    protected static String setClumnRowCountString(Command command, String orderBy){
        return "ROW_NUMBER() OVER (" +setOrderByClause(command, orderBy )+ ") AS RowNumber";
    }

    protected static int[] getSkipAndTopValuesToUseInPaging(Command command){
        int skip = command.getParams().getParamInt("skip") + 1;
        int top = skip + command.getParams().getParamInt("top") - 1;
        int[] val= {skip, top};
        return val;
    }

}