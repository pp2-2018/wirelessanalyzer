package validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Pipe;


public class SyntaxValidatorTest {
    SyntaxValidator stringSintaxFilter;
    Pipe<String> in;
    Pipe<String> out;
    String casoA="Filter1 Filter2 Filter3";
    String casoB="Filter1 date_Filter(10/08/2015) Filter3";
    String casoC="Filter1 date_Filter(10-08/2015) Filter3 ";
    String casoD="Filter1(variable) date_Filter(10/08/2015) Filter3";

    String casoE="()Filter1 date_Filter(10/08/2015) Filter3";
    String casoF="Filter1 date_Filter() Filter3";
    String casoG="( )Filter1 date_Filter(10/08/2015) Filter3";
    String casoH="Filter1 date_Filter (10/08/2015)Filter3";


    @Before
    public void setUp(){
        stringSintaxFilter = new SyntaxValidator();
    }
    @Test
    public void correctSintaxTest(){

       Assert.assertTrue(testAll(casoA));
       Assert.assertTrue(testAll(casoB));
       Assert.assertTrue(testAll(casoC));
       Assert.assertTrue(testAll(casoD));

    }

    @Test
    public void incorrectSintaxTest(){
        Assert.assertFalse(testAll(casoE));
        Assert.assertFalse(testAll(casoF));
        Assert.assertFalse(testAll(casoG));
        Assert.assertFalse(testAll(casoH));

    }

    public boolean testAll(String caso){
        boolean ret=true;
        for (String filtro:
        caso.split("\\s")) {
            ret=ret&&stringSintaxFilter.test(filtro);
            
        }
        return ret;
        
    }


    @Test
    public void hasParameter() {
        Assert.assertTrue(stringSintaxFilter.hasParameter(casoB.split("\\s")[1]));
        Assert.assertFalse(stringSintaxFilter.hasParameter(casoB.split("\\s")[0]));

    }

    @Test
    public void extractParameter() {
        Assert.assertEquals("10/08/2015",stringSintaxFilter.extractParameter(casoB.split("\\s")[1]));
        Assert.assertEquals("",stringSintaxFilter.extractParameter(casoB.split("\\s")[0]));
    }
}
