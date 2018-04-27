package pipeAndFilter;

import org.junit.Assert;
import org.junit.Test;

import model.RawPackage;
import pipeAndFilter.filters.rawPackageFilter.RawPackageFilter;
import pipeAndFilter.impl.QueuePipe;

public class RawPackageFilterTest {

	@Test
	public void readOneByteTest() {
		
		Pipe<Byte> pipe1 = new QueuePipe<>();
		Pipe<RawPackage> pipe2 = new QueuePipe<>();
		
		pipe1.accept((byte)0x01);
		pipe1.accept((byte)0x02);
		pipe1.accept((byte)0x03);
		pipe1.accept((byte)0x04);
		pipe1.accept((byte)0xcc);
		pipe1.closeForWritting();
		
		RawPackageFilter filter = new RawPackageFilter(pipe1, pipe2);
		
		filter.process();
		
		Assert.assertTrue(pipe2.isEmpty());
		
	}
	
	@Test
	public void readAllBytesTest() {
		
		byte[] bytes = new byte[] {0x01, 0x02, 0x03, 0x04};
		RawPackage rawPackage = new RawPackage(bytes);
		Pipe<Byte> pipe1 = new QueuePipe<>();
		Pipe<RawPackage> pipe2 = new QueuePipe<>();
		
		pipe1.accept(bytes[0]);
		pipe1.accept(bytes[1]);
		pipe1.accept(bytes[2]);
		pipe1.accept(bytes[3]);
		pipe1.accept((byte)0xcc);
		pipe1.closeForWritting();
		
		RawPackageFilter filter = new RawPackageFilter(pipe1, pipe2);
		
		while(!pipe1.isEmpty()) {
			filter.process();
		}
		
		Assert.assertEquals(rawPackage, pipe2.retireve());
		Assert.assertTrue(pipe2.isEmpty());
		
	}
	
}
