package Evaluator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ByteInOffsetExpressionTest {

    private Expression byteInOffsetExpression;


    @Test
    public void interpret() {
        byteInOffsetExpression = new ByteInOffsetExpression((byte)0x3, (byte) 0x3f);
        String frame = "4400123fac";

        assertTrue(byteInOffsetExpression.interpret(frame));
    }

    @Test
    public void interpretFalse() {
        byteInOffsetExpression = new ByteInOffsetExpression((byte)0x4,(byte)0xaf);
        String frame = "4400123fac";

        assertFalse(byteInOffsetExpression.interpret(frame));
    }

    @Test(expected = RuntimeException.class)
    public void interpretOffsetOutOfBound() {
        byteInOffsetExpression = new ByteInOffsetExpression((byte)0xf, (byte)0xaf);
        String frame = "4400123fac";

        assertTrue(byteInOffsetExpression.interpret(frame));
    }

}