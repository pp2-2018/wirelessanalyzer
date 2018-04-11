package Evaluator;

public class ByteInOffsetExpression extends Expression {

    private int  offset;
    private int byteToCompare;
    private controller.HexStringToByteArray converter;

    public ByteInOffsetExpression(int offset, int byteToCompare) {
        this.offset = offset;
        this.byteToCompare = byteToCompare;
        this.converter = new controller.HexStringToByteArray();
    }

    @Override
    public boolean interpret(String context) {
        byte[] hexa = converter.convert(context);

        return hexa[offset] == byteToCompare;
    }


}
