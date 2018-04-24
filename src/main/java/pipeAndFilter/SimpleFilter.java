package pipeAndFilter;

public interface SimpleFilter<I,O> {

    public O transform(I input);

}
