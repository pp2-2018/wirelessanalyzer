package Evaluator;

public abstract class Expression<T> {
    public abstract boolean interpret(T context);
}
