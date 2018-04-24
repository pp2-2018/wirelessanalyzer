package pipeAndFilter;

public interface Generator<O> extends Processable {

	public O generate();
	
	public boolean canGenerate();
	
}
