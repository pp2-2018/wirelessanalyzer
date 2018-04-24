package pipeAndFilter;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class SimpleFilterImplTest {

	@Test
	public void test() {
		
		Pipe<String> input = new QueuePipe<>();
		Pipe<Integer> output = new QueuePipe<>();
		
		SimpleFilterImpl<String, Integer> filter = mock(SimpleFilterImpl.class);
		
		filter.transform(input, output);
		
		verify(filter, times(1)).transform(input, output);
		
	}
	
	
}
