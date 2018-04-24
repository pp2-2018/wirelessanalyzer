package pipeAndFilter;

import static org.mockito.Mockito.*;

import org.junit.Test;

import pipeAndFilter.impl.SimpleFilterImpl;

public class SimpleFilterImplTest {

	@Test
	public void test() {
		
		SimpleFilterImpl<String, Integer> filter = mock(SimpleFilterImpl.class);
		when(filter.transform(anyString())).thenReturn(2);
		
		filter.transform("2");
		
		verify(filter, times(1)).transform(anyString());
		
	}
	
	
}
