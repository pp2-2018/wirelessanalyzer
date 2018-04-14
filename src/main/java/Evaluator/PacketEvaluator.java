package Evaluator;

import Model.RawPackage;

public class PacketEvaluator extends Evaluator<RawPackage>{


    public PacketEvaluator(Expression expressions) {
        super(expressions);
    }

    @Override
    public boolean evaluate(RawPackage rawPackage) {
        return super.expressions.interpret(rawPackage);
    }
}
