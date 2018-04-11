package Evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Evaluator<E> {

    private Expression expressions;

    public Evaluator(Expression expressions) {

        this.expressions = expressions;

    }

    public abstract boolean evaluate(E e);

}
