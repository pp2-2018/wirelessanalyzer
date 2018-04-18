package packageBuilder;

import evaluator.Expression;
import model.Package;
import model.RawPackage;

public class PackageHandler {

	private PackageHandler next;
	private Expression<RawPackage> filter;
	private PackageBuilder builder;
	
	
	public PackageHandler(Expression<RawPackage> filter, PackageBuilder builder) {
		super();
		this.filter = filter;
		this.builder = builder;
	}

	public void setNext(PackageHandler next){
		if(next == null);
			//TODO throw
		this.next = next;
	}
	
	public Package handlePackage(RawPackage rawPackage){
		
		if(filter.interpret(rawPackage)){
			return builder.build(rawPackage);
		}
		if(next == null);
			//throw
		return next.handlePackage(rawPackage);
		
	}
	
}
