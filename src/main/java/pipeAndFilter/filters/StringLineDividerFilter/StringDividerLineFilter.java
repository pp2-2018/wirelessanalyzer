package pipeAndFilter.filters.StringLineDividerFilter;

import java.util.List;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class StringDividerLineFilter extends SimpleFilterImpl<String, String>{

	public StringDividerLineFilter(Pipe<String> inputPipe, Pipe<String> outputPipe) {
		super(inputPipe, outputPipe);
	}

	@Override
	public void transform(Pipe<String> input, Pipe<String> output) {
		
		throw new RuntimeException();
		
		
	}


}
