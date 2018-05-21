package pipeAndFilter;

public interface simpleOutputSplitter<T> {
    public void transport(Pipe<T> input, Pipe<T>... outputs);

}
