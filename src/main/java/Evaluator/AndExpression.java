package Evaluator;

import java.util.Arrays;
import java.util.List;

public class AndExpression<T> extends Expression<T> {

    List<Expression<T>> expressionList;

    public AndExpression(Expression<T>... expressions) {
        this.expressionList = Arrays.asList(expressions);
    }

    @Override
    public boolean interpret(T context) {
        boolean toRet = true;
        for (Expression e : expressionList)
            toRet &= e.interpret(context);

        return toRet;
    }
}
