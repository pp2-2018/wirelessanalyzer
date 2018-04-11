package Evaluator;

import java.util.ArrayList;
import java.util.List;

public class OrExpression extends Expression {

    List<Expression> expressionList;

    public OrExpression(Expression... expressions) {
        this.expressionList = new ArrayList<>();

    }

    @Override
    public boolean interpret(String context) {
        boolean toRet = false;
        for (Expression e : expressionList)
            toRet|= e.interpret(context);

        return toRet;
    }
}
