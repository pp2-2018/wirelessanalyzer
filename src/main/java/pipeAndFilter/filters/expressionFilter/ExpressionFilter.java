package pipeAndFilter.filters.expressionFilter;

import evaluator.Evaluator;
import evaluator.Expression;
import model.RawPackage;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.DoubleOutputFilterImpl;

public class ExpressionFilter extends DoubleOutputFilterImpl<RawPackage, RawPackage, RawPackage>{

	private Expression<RawPackage> expression;
	
	public ExpressionFilter(Pipe<RawPackage> inputPipe, Pipe<RawPackage> outputPipeA, Pipe<RawPackage> outputPipeB, Expression<RawPackage> expression) {
		super(inputPipe, outputPipeA, outputPipeB);
		
		this.expression = expression; 
	}

	@Override
	public void transform(Pipe<RawPackage> inputPipe, Pipe<RawPackage> outputPipeA, Pipe<RawPackage> outputPipeB) {
		
		RawPackage input = inputPipe.retireve();
		
		if(expression.interpret(input))
			outputPipeB.accept(input);
		else{
			outputPipeA.accept(input);
		}
		
		
	}
	
	

	
}
