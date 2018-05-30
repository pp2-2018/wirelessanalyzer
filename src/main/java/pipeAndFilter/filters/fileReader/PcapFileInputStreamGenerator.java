package pipeAndFilter.filters.fileReader;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.CannotReadFileException;
import fileReader.PcapFileInputStream;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.parameters.FileListParameter;
import pipeAndFilter.parameters.Parametrized;

public class PcapFileInputStreamGenerator extends GeneratorImpl<Byte>{

	private List<File> files;
	private int index;
	private PcapFileInputStream fileReader;
	
	public PcapFileInputStreamGenerator(Pipe<Byte> outputPipe, List<File> files) {
		super(outputPipe);
		
		this.files = files;
		index = 0;
		
		createInputStream();
		
	}
	
	public PcapFileInputStreamGenerator(Pipe<Byte> outputPipe, File file) {
		
		this(outputPipe, Arrays.asList( new File[] {file}));
		
	}

	
	@Override
	public void put(Pipe<Byte> output) {
		
		try {
			
			
			if(fileReader.available() > 0) {

				int filebyte = fileReader.read();
				Byte b = Byte.valueOf((byte)filebyte);
				output.accept(b);
				}
			else {
				createInputStream();
				
			}
			
			
		} catch (IOException e1) {

			throw new CannotReadFileException(files.get(index).getAbsolutePath());
		}

	}

	@Override
	public boolean canGenerate() {
		try {
			return this.index < files.size() || fileReader.available() > 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void createInputStream() {
		try {
			fileReader = new PcapFileInputStream(files.get(index));
		} catch (FileNotFoundException e) {

			throw new CannotReadFileException(files.get(index).getAbsolutePath());
		}
		index++;
	}

}
