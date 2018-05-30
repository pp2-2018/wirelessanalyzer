package pipeAndFilter.parameters;

import java.io.File;
import java.util.List;

public interface FilterParameter<T> {
	
	public T fromString(String paramStr);
	
}
