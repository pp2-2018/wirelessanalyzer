package PackageBuilder;

import Evaluator.Expression;
import Model.RawPackage;

public class PackageBuilder {

	private PackageBuilder next;
	private Expression<RawPackage> filter;
	
	
	public PackageBuilder() {
		
	}
	
	public void setNext(PackageBuilder next){
		if(next == null);
			//TODO throw
		this.next = next;
	}
	
	public Package buildPackage(RawPackage rawPackage){
		
		if(filter.interpret(rawPackage)){
			/*
			 * return configurationBasedBuilder.build(rawPackage);
			 * 
			 */
		}
		if(next == null);
			//throw
		return next.buildPackage(rawPackage);
		
	}
	
}
