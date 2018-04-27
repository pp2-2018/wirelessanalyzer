package pipeAndFilter.filters.PackageBuilderFilter;

import model.RawPackage;
import packageBuilder.PackageBuilder;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import evaluator.Expression;
import model.Package;
import model.Pair;

public class PackageBuilderNormalizer extends SimpleFilterImpl<RawPackage, Package> {

	private Map<Expression<RawPackage>, PackageBuilder> routes;
	
	public PackageBuilderNormalizer(Pipe<RawPackage> inputPipe, Pipe<Package> outputPipe) {
		super(inputPipe, outputPipe);
		this.routes = new HashMap<>();
	}

	@Override
	public void transform(Pipe<RawPackage> input, Pipe<Package> output) {
		
		RawPackage raw = input.retireve();
		
		this.routes.forEach((e, b) -> {
			if(e.interpret(raw))
				output.accept(b.build(raw));
			
		});
		
	}
	
	public void addRoute(Expression<RawPackage> expression, PackageBuilder builder) {
		
		this.routes.put(expression, builder);
		
	}

}
