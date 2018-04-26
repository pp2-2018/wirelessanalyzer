package pipeAndFilter.filters.rawPackageFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.RawPackage;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import rawPacageFactory.RawPacageFactory;

public class RawPackage32BytesFilter extends SimpleFilterImpl<Byte, RawPackage>{

	private List<Byte> byteBuffer;
	private int byteQuantity;
	private RawPacageFactory rawPacageFactory;
	
	public RawPackage32BytesFilter(Pipe<Byte> inputPipe, Pipe<RawPackage> outputPipe) {
		super(inputPipe, outputPipe);

		this.byteQuantity = 32;
		this.byteBuffer = new ArrayList<Byte>(byteQuantity);
		
		this.rawPacageFactory = new RawPacageFactory();
	}

	@Override
	public void transform(Pipe<Byte> input, Pipe<RawPackage> output) {
		
			
		Byte inputByte = input.retireve();
		
		if(inputByte.byteValue() == (byte)0xcc) {
			RawPackage rawPackage = rawPacageFactory.getFromList(byteBuffer);
			output.accept(rawPackage);
			
			byteBuffer.clear();
			
		}
		else {

			byteBuffer.add(inputByte);
		}

	}

}
