package pipeAndFilter;

public interface Sink<I> extends Processable{

	void take(Pipe<I> input);
	
}
