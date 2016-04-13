package pt.isel.ls.commandline.process;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test ParamGetter getModelFromParams method.
 */
public class ParamGetterTests {
    private final String rating = "rating=5";
    private final String movieNameAndYear = "title=pulp+fiction&releaseYear=1994";
    private final String reviewFullParams = "reviewerName=Michael+Java&reviewSummary=This is a software test!&" +
            "review=Really, I mean it, it was a good movie but this is only a software test dude!&rating=5";

    @Test
    public void onlyRatingAsParamTest(){
       assertEquals(5, (int)new ParamGetter().getParams(rating).getParamInt("rating"));
    }

    @Test
    public void movieNameAndYearAsParamsNameTest(){
        assertEquals("pulp fiction", new ParamGetter().getParams(movieNameAndYear).getParamString("title"));
    }

    @Test
    public void movieNameAndYearAsParamsYearTest(){
        assertEquals(1994, (int)new ParamGetter().getParams(movieNameAndYear).getParamInt("releaseYear"));
    }

    @Test
    public void reviewFullParamsNameTest(){        ;
        assertEquals("Michael Java", new ParamGetter().getParams(reviewFullParams).getParamString("reviewerName"));
    }

    @Test
    public void reviewFullParamsSummaryTest(){
        assertEquals("This is a software test!", new ParamGetter().getParams(reviewFullParams).getParamString("reviewSummary"));
    }

    @Test
    public void reviewFullParamsReviewTest(){
        assertEquals("Really, I mean it, it was a good movie but this is only a software test dude!",
                new ParamGetter().getParams(reviewFullParams).getParamString("review"));
    }

    @Test
    public void reviewFullParamsRatingTest(){
        assertEquals(5, (int)new ParamGetter().getParams(reviewFullParams).getParamInt("rating"));
    }

}
