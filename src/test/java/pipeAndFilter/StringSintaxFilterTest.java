package pipeAndFilter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.filters.StringSintaxFilter;
import pipeAndFilter.impl.QueuePipe;

public class StringSintaxFilterTest {
    StringSintaxFilter stringSintaxFilter;
    Pipe<String> in;
    Pipe<String> out;
    String casoA="Filter1 Filter2 Filter3";
    String casoB="Filter1 date_Filter(10/08/2015 ) Filter3";
    String casoC=" Filter1 date_Filter(10-08/2015) Filter3 ";
    String casoD="Filter1(variable) date_Filter ( 10/08/2015 ) Filter3";

    String casoE="()Filter1 date_Filter(10/08/2015) Filter3";
    String casoF="Filter1 date_Filter() Filter3";
    String casoG="( )Filter1 date_Filter(10/08/2015) Filter3";
    String casoH="Filter1 date_Filter (10/08/2015)Filter3";


    @Before
    public void setUp(){
        in=new QueuePipe<>();
        out=new QueuePipe<>();
        stringSintaxFilter = new StringSintaxFilter(in,out);
    }
    @Test
    public void correctSintaxTest(){
       Assert.assertEquals(casoA,testFilter(casoA));
       Assert.assertEquals(casoB,testFilter(casoB));
       Assert.assertEquals(casoC,testFilter(casoC));
       Assert.assertEquals(casoD,testFilter(casoD));

    }

    @Test
    public void incorrectSintaxTest(){
        Assert.assertNotEquals(casoE,testFilter(casoE));
        Assert.assertNotEquals(casoF,testFilter(casoF));
        Assert.assertNotEquals(casoG,testFilter(casoG));
        Assert.assertNotEquals(casoH,testFilter(casoH));

    }

    private String testFilter(String inputString){
        in.accept(inputString);
        stringSintaxFilter.process();
        return out.retireve();
    }



}
