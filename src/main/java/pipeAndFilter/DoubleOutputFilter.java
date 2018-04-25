package pipeAndFilter;

public interface DoubleOutputFilter<I, OA, OB> extends Processable{

	void transform(Pipe<I> inputPipe, Pipe<OA> outputPipeA, Pipe<OB> outputPipeB);
	
}
