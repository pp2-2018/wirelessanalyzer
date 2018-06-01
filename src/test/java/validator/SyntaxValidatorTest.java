package validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Pipe;


public class SyntaxValidatorTest {
    SyntaxValidator stringSintaxFilter;
    Pipe<String> in;
    Pipe<String> out;
    String casoA="testGenerator(variable) testFilter testSink";
    String casoB="testGenerator(variable) testFilter testSink";
    String casoC="testGenerator(variable) testFilter testSink ";
    String casoD="testGenerator testFilter testSink";

    String casoE="()testGenerator testFilter testSink";
    String casoF="testGenerator testFilter() testSink";
    String casoG="( )testGenerator testFilter testSink";
    String casoH="testGenerator( variable) testFilter testSink";


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
        Assert.assertTrue(stringSintaxFilter.hasParameter(casoB.split("\\s")[0]));
        Assert.assertFalse(stringSintaxFilter.hasParameter(casoB.split("\\s")[1]));

    }

    @Test
    public void extractParameter() {
        Assert.assertEquals("variable",stringSintaxFilter.extractParameter(casoB.split("\\s")[0]));
        Assert.assertEquals("",stringSintaxFilter.extractParameter(casoD.split("\\s")[0]));
    }
}
