package pipeAndFilter.filters.fileReader;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileReader.PcapFileInputStream;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.GeneratorImpl;

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
	
	@Override
	public void put(Pipe<Byte> output) {
		
		try {
			
			int filebyte = fileReader.read();
			
			if(fileReader.available() >= 0) {

				Byte b = Byte.valueOf((byte)filebyte);
				output.accept(b);
				}
			else {
				createInputStream();
				
			}
			
			
		} catch (IOException e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public boolean canGenerate() {
		try {
			return this.index <= files.size() && fileReader.available() > 0;
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
			e.printStackTrace();
		}
		index++;
	}

}
