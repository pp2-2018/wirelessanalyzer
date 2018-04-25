package pipeAndFilter.funnel;

import model.Package;
import model.RawPackage;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.FunnelImpl;
import pipeAndFilter.impl.PipeSystem;

public class SimplePackageFunnel extends FunnelImpl<Package, Package>{

	public SimplePackageFunnel(PipeSystem<Package> inputPipes, Pipe<Package> outputPipe) {
		super(inputPipes, outputPipe);
	}

	@Override
	public void collect(Pipe<Package> inputPipe, Pipe<Package> outputPipe) {
		
		while(inputPipe.canRetrieve()) {
			Package p = inputPipe.retireve();
			outputPipe.accept(p);
		}
		
	}

}
