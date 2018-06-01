package pipeAndFilter.filters.rawPackageFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.RawPackage;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.parameters.Parametrized;
import rawPacageFactory.RawPacageFactory;

public class RawPackageFilter extends SimpleFilterImpl<Byte, RawPackage>{

	private List<Byte> byteBuffer;
	private RawPacageFactory rawPacageFactory;
	
	public RawPackageFilter(Pipe<Byte> inputPipe, Pipe<RawPackage> outputPipe) {
		super(inputPipe, outputPipe);

		this.byteBuffer = new ArrayList<Byte>(32);
		
		this.rawPacageFactory = new RawPacageFactory();
	}
	
	@Parametrized
	public RawPackageFilter() {
		super(null, null);

		this.byteBuffer = new ArrayList<Byte>(32);
		
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
