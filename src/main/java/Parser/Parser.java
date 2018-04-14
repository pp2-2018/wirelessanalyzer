package Parser;

import java.util.ArrayList;
import java.util.List;

import Evaluator.AndExpression;
import Evaluator.ByteInOffsetExpression;
import Evaluator.Expression;
import Evaluator.RawPackage;

public class Parser {

	String paquete = "3 3f";
	
	public Expression<RawPackage> parse(PackageRulesFile packageRulesFile){
		
		List<Expression<RawPackage>> expressions = new  ArrayList<>();
		
		for (PackageRule r: packageRulesFile){
			expressions.add(new ByteInOffsetExpression(r.getOffset(), r.getByte()));
		}
		
		return new AndExpression<RawPackage>(expressions);
	}
	
	
}
