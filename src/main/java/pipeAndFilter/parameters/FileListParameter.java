package pipeAndFilter.parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListParameter extends FilterParameter<List<File>>{

	public FileListParameter(String paramStr) {
		super(paramStr);
	}

	@Override
	protected List<File> fromString(String paramStr) {
		
		if(paramStr == null || paramStr.isEmpty())
			throw new IllegalArgumentException("Illegal string: " + paramStr);
		
		String[] filesStr = paramStr.split(";");
		ArrayList<File> toRet = new ArrayList<>(filesStr.length);
		
		for (String fileStr : filesStr) 
			toRet.add(new File(fileStr));
		
		return toRet;
		
	}

	
}
