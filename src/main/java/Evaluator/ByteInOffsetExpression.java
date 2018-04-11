package Evaluator;

import FileReader.HexStringToByteArray;

public class ByteInOffsetExpression extends Expression {

    private byte  offset;
    private byte byteToCompare;
    private HexStringToByteArray converter;

    public ByteInOffsetExpression(byte offset, byte byteToCompare) {
        this.offset = offset;
        this.byteToCompare = byteToCompare;
        this.converter = new HexStringToByteArray();
    }

    @Override
    public boolean interpret(String context) {
        byte[] hexa = converter.convert(context);
        for (int i = 0; i < hexa.length; i++) ;

        return hexa[offset] == byteToCompare;
    }


}
