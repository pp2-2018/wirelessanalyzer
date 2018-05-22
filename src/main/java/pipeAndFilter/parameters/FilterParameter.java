package pipeAndFilter.parameters;

import java.io.File;
import java.util.List;

public abstract class FilterParameter<T> {

	private T param;
	
	public FilterParameter(String paramStr) {
		this.param = fromString(paramStr);
	}
	
	
	public T getParameter(){
		return param;
	}
	
	abstract protected T fromString(String paramStr);
	
}
