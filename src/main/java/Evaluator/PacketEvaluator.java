package Evaluator;

public class PacketEvaluator extends Evaluator<String> {

    public PacketEvaluator(Expression expressions) {
        super(expressions);
    }

    @Override
    public boolean evaluate(String s) {
        return super.expressions.interpret(s);
    }
}
