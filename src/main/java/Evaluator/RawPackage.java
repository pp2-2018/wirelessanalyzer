package Evaluator;

public class RawPackage {

    private byte[] data;

    public RawPackage(byte [] data){

        this.data = data;

    }

    public byte getByteAt(int offset){

        if(offset > data.length);
            //TODO tirar excepcion

        return data[offset];

    }

}
