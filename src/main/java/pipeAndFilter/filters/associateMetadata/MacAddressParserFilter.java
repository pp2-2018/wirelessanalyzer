package pipeAndFilter.filters.associateMetadata;

import model.device.MacAddress;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class MacAddressParserFilter extends SimpleFilterImpl<String, MacAddress>{

	public MacAddressParserFilter(Pipe<String> inputPipe, Pipe<MacAddress> outputPipe) {
		super(inputPipe, outputPipe);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void transform(Pipe<String> input, Pipe<MacAddress> output) {
		String macString = input.retireve();
		output.accept(stringTobyteArray(macString));
		
	}

	private MacAddress stringTobyteArray(String macString) {
		
		return new MacAddress(null);
	}
	
	

}
