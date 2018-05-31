package pipeAndFilter.filters.associateMetadata;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.device.MacAddress;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.QueuePipe;

public class MacAddressParserFilterTest {
	
	
	private MacAddressParserFilter stringParserFilter;
	private Pipe<String> inputPipe;
	private Pipe<MacAddress> outputPipe;

	@Test
	public void testStringTobyteArray() throws Exception {
		stringParserFilter = getFilter(new String[] {"4a-88-7f-41-12-47", "ff-ee-dd-11-bc-4a"});
		
	}

	private MacAddressParserFilter getFilter(String[] input) {
		inputPipe = getPipe(input);
		
		outputPipe = new QueuePipe<>();
		
		return new MacAddressParserFilter(inputPipe, outputPipe);
	}

	private Pipe<String> getPipe(String[] strings) {
		List<String> fileNames = Arrays.asList(strings);
		Pipe<String> toRet = new QueuePipe<>();
		fileNames.forEach(name -> toRet.accept(name));
		toRet.closeForWritting();
		return toRet;
	}
}
