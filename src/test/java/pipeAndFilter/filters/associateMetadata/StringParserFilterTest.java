package pipeAndFilter.filters.associateMetadata;

import java.util.List;

import static org.junit.Assert.assertTrue;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.QueuePipe;

public class StringParserFilterTest {
	
	private StringParserFilter stringParserFilter;
	private Pipe<String> inputPipe;
	private Pipe<String> outputPipe;
	private final String regex = "([a-f0-9]{2}-){5}[a-f0-9]{2}";
	
	@Before
	public void tearDown() {
		stringParserFilter = null;
		inputPipe = null;

		outputPipe = null;
	}
	
	@Test
	public void testFilter() {
		stringParserFilter = getFilter(new String[] {"/home/user/1_4a-88-7f-41-12-47.pcap", 
		"/home/user12-1/1_ff-ee-dd-11-bc-4aq.pcap"}, regex);
	
		List<String> toCompare = Arrays.asList(new String[] {"4a-88-7f-41-12-47",
		"ff-ee-dd-11-bc-4a"});
		
		while(!inputPipe.isEmpty()) 
			stringParserFilter.process();
		
		while(!outputPipe.isEmpty()) {
			String compare = outputPipe.retireve();
			assertTrue(toCompare.contains(compare));
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFilterWrongSrting() {
		stringParserFilter = getFilter(new String[] {"/home/user/1_4a-8d8-7f-41-12-47.pcap", 
		"/home/user12-1/1_ff-ee-dd-11-bc-4aq.pcap"}, regex);
	
		List<String> toCompare = Arrays.asList(new String[] {"4a-88-7f-41-12-47",
		"ff-ee-dd-11-bc-4a"});
		
		while(!inputPipe.isEmpty()) 
			stringParserFilter.process();
		
		while(!outputPipe.isEmpty()) {
			String compare = outputPipe.retireve();
			assertTrue(toCompare.contains(compare));
		}
	}
	
	private StringParserFilter getFilter(String[] input, String regex) {
		inputPipe = getPipe(input);
		
		outputPipe = new QueuePipe<>();
		
		return new StringParserFilter(inputPipe, outputPipe, regex);
	}

	private Pipe<String> getPipe(String[] strings) {
		List<String> fileNames = Arrays.asList(strings);
		Pipe<String> toRet = new QueuePipe<>();
		fileNames.forEach(name -> toRet.accept(name));
		toRet.closeForWritting();
		return toRet;
	}
}
