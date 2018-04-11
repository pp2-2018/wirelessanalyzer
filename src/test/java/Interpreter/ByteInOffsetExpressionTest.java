package Interpreter;

import Interpreter.ByteInOffsetExpression;
import Interpreter.Expression;
import org.junit.Test;

import static org.junit.Assert.*;

public class ByteInOffsetExpressionTest {

    private Expression byteInOffsetExpression;


    @Test
    public void interpret() {
        byteInOffsetExpression = new ByteInOffsetExpression(0x3, 0x3f);
        String frame = "4400123fac";

        assertTrue(byteInOffsetExpression.interpret(frame));
    }

    @Test
    public void interpretFalse() {
        byteInOffsetExpression = new ByteInOffsetExpression(0x4, 0xaf);
        String frame = "4400123fac";

        assertFalse(byteInOffsetExpression.interpret(frame));
    }

    @Test(expected = RuntimeException.class)
    public void interpretOffsetOutOfBound() {
        byteInOffsetExpression = new ByteInOffsetExpression(0xf, 0xaf);
        String frame = "4400123fac";

        assertTrue(byteInOffsetExpression.interpret(frame));
    }

}