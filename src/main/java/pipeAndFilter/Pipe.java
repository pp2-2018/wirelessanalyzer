package pipeAndFilter;

public interface Pipe<T> {
    
    public void accept(T t);
    
    public T retireve();
    
    public void closeForWritting();
    
    public boolean canRetrieve();
    
    public boolean isClosed();
    
    public boolean isEmpty();

}
