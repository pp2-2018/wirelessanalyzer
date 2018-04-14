package Parser;

import java.util.Iterator;
import java.util.List;

public class PackageRulesFile implements Iterable<PackageRule>{

	private List<PackageRule> rules;
	
	
	@Override
	public Iterator<PackageRule> iterator() {
		
		return this.rules.iterator();
		
	}

	
	
	
}
