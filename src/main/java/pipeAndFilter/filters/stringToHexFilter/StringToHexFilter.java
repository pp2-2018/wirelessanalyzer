package pipeAndFilter.filters.stringToHexFilter;

import fileReader.HexStringToByteArray;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class StringToHexFilter extends SimpleFilterImpl<String, byte[]>{

	private HexStringToByteArray stringToByteArray;
	
	public StringToHexFilter(Pipe<String> inputPipe, Pipe<byte[]> outputPipe) {
		super(inputPipe, outputPipe);
	
		this.stringToByteArray = new HexStringToByteArray();
	}

	@Override
	public void transform(Pipe<String> input, Pipe<byte[]> output) {

		String inputString = input.retireve();
		output.accept(this.stringToByteArray.convert(inputString));
	
	}
	

}
