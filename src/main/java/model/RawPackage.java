package model;

import java.util.Arrays;

import packageBuilder.ByteSegment;

public class RawPackage {

    private byte[] data;

    public RawPackage(byte [] data){

        this.data = data;

    }

    public byte getByteAt(int offset){

        if(offset > data.length)
            throw new IndexOutOfBoundsException("Offset exceed packet lengh. Packet lengh = " +
            data.length + ", offset = " + offset);

        return data[offset];

    }

	public byte[] getSegment(ByteSegment segment) {
		
        if(segment.getPosition() + segment.getLength() > data.length)
            throw new IndexOutOfBoundsException();
        
        byte[] a = Arrays.copyOfRange(data, segment.getPosition(), segment.getPosition() + segment.getLength());
        
        return Arrays.copyOfRange(data, segment.getPosition(), segment.getPosition() + segment.getLength());
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawPackage other = (RawPackage) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}

}
