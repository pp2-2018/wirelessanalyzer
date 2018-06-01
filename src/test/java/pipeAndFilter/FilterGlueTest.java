package pipeAndFilter;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pipeAndFilter.filters.test.TestGenerator;
import pipeAndFilter.impl.FilterSystem;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.registry.FilterGlue;

public class FilterGlueTest {

	private class TestSink<T> extends SinkImpl<T>{

		public TestSink() {
			super(null);
		}

		@Override
		public void take(Pipe<T> input) {
			System.out.println(input.retireve().toString());
		}
		
	}
	
	private class TestSimpleFilter<T> extends SimpleFilterImpl<T, T>{

		public TestSimpleFilter() {
			super(null, null);
		}

		@Override
		public void transform(Pipe<T> input, Pipe<T> output) {

			output.accept(input.retireve());
			
		}
		
	}
	
	FilterGlue filterGlue = new FilterGlue();
	
	@Test
	public void test() {
		
		List<Processable> processables = getSimpleProcessables();
		Processable gen = processables.get(0);
		
		FilterSystem system = filterGlue.glue(processables);
		Pipe<String> genPipe = (Pipe<String>) gen.getPipes()[0];
		gen.process();
		
		Assert.assertEquals(genPipe.retireve(), "1");
	}
	
	private List<Processable> getSimpleProcessables(){
		
		Processable p1 = new TestGenerator("123456789");
		Processable p2 = new TestSimpleFilter<String>();
		Processable p3 = new TestSink<String>();
		
		return Arrays.asList(new Processable[]{p1, p2, p3});
		
	}
	
	
}
