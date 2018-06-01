package pipeAndFilter.parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileParameter implements FilterParameter<File>{

	@Override
	public File fromString(String paramStr) {
		
		if(paramStr == null || paramStr.isEmpty())
			throw new IllegalArgumentException("Illegal string: " + paramStr);
		
		return new File(paramStr);
		
		
	}

	
}
