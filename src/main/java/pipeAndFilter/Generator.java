package pipeAndFilter;

public interface Generator<O> extends Processable {

	public void put(Pipe<O> outputPipe);
	
	public boolean canGenerate();
	
}
