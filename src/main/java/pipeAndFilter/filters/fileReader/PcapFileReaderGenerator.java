package pipeAndFilter.filters.fileReader;

import java.io.File;
import java.util.List;

import fileReader.PcapFileReader;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.GeneratorImpl;

public class PcapFileReaderGenerator extends GeneratorImpl<String>{

	private List<File> files;
	private int index;
	
	public PcapFileReaderGenerator(Pipe<String> outputPipe, List<File> files) {
		super(outputPipe);
		
		this.files = files;
		index = 0;
		
	}
	
	@Override
	public String generate() {
		
		PcapFileReader fileReader = new PcapFileReader(files.get(index));
		index++;
		
		return fileReader.read();
		
	}

	@Override
	public boolean canGenerate() {
		return this.index < files.size();
	}

}
