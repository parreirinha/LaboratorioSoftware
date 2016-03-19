package pt.isel.ls.command.process;

import org.junit.Test;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Rating;
import pt.isel.ls.model.Review;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test ParamGetter getModelFromParams method.
 */
public class ParamGetterTests {
    private final String rating = "rating=5";
    private final String movieNameAndYear = "title=pulp+fiction&releaseYear=1994";
    private final String reviewFullParams = " reviewerName=Michael+Java&reviewSummary=This is a software test!&" +
            "review=Really, I mean it, it was a good movie but this is only a software test dude!&rating=5";

    @Test
    public void onlyRatingAsParamTest(){
       assertEquals(5, (int)new ParamGetter().getParams(rating).getIntegers().iterator().next());
    }

    @Test
    public void movieNameAndYearAsParamsNameTest(){
        assertEquals("pulp fiction", new ParamGetter().getParams(movieNameAndYear).getParamParts().iterator().next());
    }

    @Test
    public void movieNameAndYearAsParamsYearTest(){
        assertEquals(1994, (int)new ParamGetter().getParams(movieNameAndYear).getIntegers().iterator().next());
    }

    @Test
    public void reviewFullParamsNameTest(){        ;
        assertEquals("Michael Java", new ParamGetter().getParams(reviewFullParams).getParamParts().iterator().next());
    }

    @Test
    public void reviewFullParamsSummaryTest(){
        ArrayList<String> list = (ArrayList<String>)new ParamGetter().getParams(reviewFullParams).getParamParts();
        assertEquals("This is a software test!", list.get(1));
    }

    @Test
    public void reviewFullParamsReviewTest(){
        ArrayList<String> list = (ArrayList<String>)new ParamGetter().getParams(reviewFullParams).getParamParts();
        assertEquals("Really, I mean it, it was a good movie but this is only a software test dude!",
                list.get(2));
    }

    @Test
    public void reviewFullParamsRatingTest(){
        assertEquals(5, (int)new ParamGetter().getParams(reviewFullParams).getIntegers().iterator().next());
    }

}
