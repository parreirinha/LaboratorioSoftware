package pt.isel.ls.executioncommands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliar class that contains auxiliar methods that are used in more than one linecommand
 */
public class AccessUtils {

    public static String getColumnName(int rating) {

        String[] star = {"OneStar", "TwoStar", "TreeStar", "FourStar", "FiveStar"};
        return star[rating-1];
    }

    /**
     * set values in the preparement statement
     * @param ps PreparementStatement
     * @param vals values to set
     * @throws SQLException
     */
    public static void setValuesOnPreparedStatement(PreparedStatement ps, Object... vals) throws SQLException {
    //TODO só suporta strings e int
        int i = 0;
        for (; i < vals.length; i++){
            if (vals[i] instanceof String)
                ps.setString(i+1,(String)vals[i]);
            else {
                ps.setInt(i+1, (Integer) vals[i]);
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
}
