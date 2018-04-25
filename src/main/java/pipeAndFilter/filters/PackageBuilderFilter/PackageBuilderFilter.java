package pipeAndFilter.filters.PackageBuilderFilter;

import model.RawPackage;
import packageBuilder.PackageBuilder;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import model.Package;

public class PackageBuilderFilter extends SimpleFilterImpl<RawPackage, Package> {

	private PackageBuilder builder;
	
	public PackageBuilderFilter(Pipe<RawPackage> inputPipe, Pipe<Package> outputPipe, PackageBuilder builder) {
		super(inputPipe, outputPipe);
		this.builder = builder;
	}

	@Override
	public void transform(Pipe<RawPackage> input, Pipe<Package> output) {
		
		RawPackage raw = input.retireve();
		
		model.Package built = builder.build(raw);
		
		output.accept(built);
		
	}

}
