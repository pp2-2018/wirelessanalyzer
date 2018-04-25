package pipeAndFilter.filters.fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fileReader.PcapFileInputStream;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.GeneratorImpl;

public class PcapFileInputStreamGenerator extends GeneratorImpl<byte[]>{

	private List<File> files;
	private int index;
	
	public PcapFileInputStreamGenerator(Pipe<byte[]> outputPipe, List<File> files) {
		super(outputPipe);
		
		this.files = files;
		index = 0;
		
	}
	
	@Override
	public void put(Pipe<byte[]> output) {

		PcapFileInputStream fileReader = null;
		try {
			fileReader = new PcapFileInputStream(files.get(index));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		index++;

		byte[] fileBytes = new byte[10000];
		fileReader.read(fileBytes);

		output.accept(fileBytes);

	}

	@Override
	public boolean canGenerate() {
		return this.index < files.size();
	}

}
