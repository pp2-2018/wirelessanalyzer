package Evaluator;

public abstract class Evaluator<E> {

    protected Expression expressions;

    public Evaluator(Expression expressions) {

        this.expressions = expressions;

    }

    public abstract boolean evaluate(E e);

}
