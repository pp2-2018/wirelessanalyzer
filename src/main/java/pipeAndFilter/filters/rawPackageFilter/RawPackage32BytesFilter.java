package pipeAndFilter.filters.rawPackageFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.RawPackage;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import rawPacageFactory.RawPacageFactory;

public class RawPackage32BytesFilter extends SimpleFilterImpl<byte[], RawPackage>{

	private List<Byte> byteBuffer;
	private int byteQuantity;
	private RawPacageFactory rawPacageFactory;
	
	public RawPackage32BytesFilter(Pipe<byte[]> inputPipe, Pipe<RawPackage> outputPipe) {
		super(inputPipe, outputPipe);

		this.byteQuantity = 32;
		this.byteBuffer = new ArrayList<Byte>(byteQuantity);
		
		this.rawPacageFactory = new RawPacageFactory();
	}

	@Override
	public void transform(Pipe<byte[]> input, Pipe<RawPackage> output) {
		
		while(!input.isEmpty()) {
			
			byte[] inputArray = input.retireve();
			
			for(byte b : inputArray) {
				byteBuffer.add(b);
				
				if(byteBuffer.size() == byteQuantity) {
					RawPackage rawPackage = rawPacageFactory.getFromList(byteBuffer);
					output.accept(rawPackage);
					
					byteBuffer.clear();
					
				}
			}
		}
		
	}

}
