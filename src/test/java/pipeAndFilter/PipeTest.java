package pipeAndFilter;

import org.junit.Assert;
import org.junit.Test;

import pipeAndFilter.impl.QueuePipe;

public class PipeTest {
    
    @Test
    public void test(){
        
        Pipe<String> pipe = new QueuePipe<>();
        
        pipe.accept("Element1");
        pipe.accept("Element2");
        
        Assert.assertEquals("Element2", pipe.retireve())
        
    }
    
    
}
