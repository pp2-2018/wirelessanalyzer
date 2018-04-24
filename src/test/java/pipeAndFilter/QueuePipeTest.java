package pipeAndFilter;

import org.junit.Assert;
import org.junit.Test;

import exceptions.PipeClosedException;
import pipeAndFilter.impl.QueuePipe;

public class QueuePipeTest {
    
    @Test
    public void retrieveTest(){
        
    	QueuePipe<String> pipe = new QueuePipe<>();
        
        pipe.accept("Element1");
        pipe.accept("Element2");
        
        Assert.assertEquals("Element1", pipe.retireve());
        Assert.assertEquals("Element2", pipe.retireve());
        
    }
    
    @Test
    public void canRetrieveTest() {

    	QueuePipe<String> pipe = new QueuePipe<>();
        
        pipe.accept("Element1");
        pipe.accept("Element2");
        pipe.closeForWritting();
        
        Assert.assertTrue(pipe.canRetrieve());
        
        Assert.assertEquals("Element1", pipe.retireve());
        Assert.assertEquals("Element2", pipe.retireve());

        Assert.assertFalse(pipe.canRetrieve());
    }
    
    @Test(expected=PipeClosedException.class)
    public void retrieveWhenCantTest() {
    	QueuePipe<String> pipe = new QueuePipe<>();
        
        pipe.accept("Element1");
        pipe.accept("Element2");
        pipe.closeForWritting();
        
        pipe.retireve();
        pipe.retireve();
        pipe.retireve();
    }
    
    @Test(expected=PipeClosedException.class)
    public void acceptWhenCant() {
    	QueuePipe<String> pipe = new QueuePipe<>();
        
        pipe.accept("Element1");
        pipe.accept("Element2");
        pipe.closeForWritting();

        pipe.accept("Element3");
    }
    
    
}
