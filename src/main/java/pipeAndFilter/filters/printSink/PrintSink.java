package pipeAndFilter.filters.printSink;

import java.util.function.Function;

import pipeAndFilter.Pipe;
import pipeAndFilter.Sink;
import pipeAndFilter.impl.SinkImpl;

public class PrintSink<T> extends SinkImpl<T>{

	private Function<T, String> toStringFunction;
	
	public PrintSink(Pipe<T> inputPipe, Function<T, String> toStringFunction) {
		super(inputPipe);
		this.toStringFunction = toStringFunction;
		
	}

	@Override
	public void take(Pipe<T> input) {
		
		T inputObj = input.retireve();
		
		
		System.out.println(toStringFunction.apply(inputObj));
		
	}


}
