package packageBuilder;

public class ByteSegment {

	private int position;
	private int length;
	
	public ByteSegment(int position, int length) {
		super();
		this.position = position;
		this.length = length;
	}

	public int getPosition() {
		return position;
	}

	public int getLength() {
		return length;
	}
	
}
