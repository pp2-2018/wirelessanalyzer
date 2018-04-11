package Evaluator;

import java.util.ArrayList;
import java.util.List;

public class AndExpression extends Expression {

    List<Expression> expressionList;

    public AndExpression(Expression... expressions) {
        this.expressionList = new ArrayList<>();

    }

    @Override
    public boolean interpret(String context) {
        boolean toRet = true;
        for (Expression e : expressionList)
            toRet &= e.interpret(context);

        return toRet;
    }
}
