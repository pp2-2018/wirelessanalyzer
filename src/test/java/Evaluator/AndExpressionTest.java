package Evaluator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AndExpressionTest {

    private Expression andExpression;
    private String expression = "40a3159c80";

    @Test
    public void interpret() {
        Expression expression1 = new ByteInOffsetExpression((byte)0x00, (byte)0x40);
        Expression expression2 = new ByteInOffsetExpression((byte)0x04, (byte)0x80);
        andExpression = new AndExpression(expression1, expression2);

        assertTrue(andExpression.interpret(expression));

    }

    @Test
    public void interpret2() {
        Expression expression1 = new ByteInOffsetExpression((byte)0x0,(byte) 0x4);
        Expression expression2 = new ByteInOffsetExpression((byte)0x4, (byte)0x80);
        andExpression = new AndExpression(expression1, expression2);

        assertFalse(andExpression.interpret(expression));

    }

    @Test
    public void interpret3() {
        Expression expression1 = new ByteInOffsetExpression((byte)0x0, (byte)0x4);
        Expression expression2 = new ByteInOffsetExpression((byte)0x4, (byte)0x10);
        andExpression = new AndExpression(expression1, expression2);

        assertFalse(andExpression.interpret(expression));

    }

}