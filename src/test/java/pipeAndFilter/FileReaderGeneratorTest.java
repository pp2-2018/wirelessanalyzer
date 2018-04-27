package pipeAndFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.impl.QueuePipe;

public class FileReaderGeneratorTest {

	private File f1 = null;
	private File f2 = null;
	
	@Before
	public void before() {
		try {
			f1 = File.createTempFile("testFile", ".pcap");
			f2 = File.createTempFile("testFile2", ".pcap");
			
			FileOutputStream fos1 = new FileOutputStream(f1);
			fos1.write(new byte[] {0x01, 0x02, 0x03});
			fos1.close();

			FileOutputStream fos2 = new FileOutputStream(f2);
			fos2.write(new byte[] {0x0A, 0x0B, 0x0C});
			fos2.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void oneByteTest() {
		
		Pipe<Byte> pipe = new QueuePipe<>();
		
		PcapFileInputStreamGenerator generator = new PcapFileInputStreamGenerator(pipe, f1);
		
		generator.process();
		
		Assert.assertEquals(0x01, pipe.retireve().byteValue());
		
	}
	
	
	@Test
	public void oneFileTest() {
		
		Pipe<Byte> pipe = new QueuePipe<>();
		
		PcapFileInputStreamGenerator generator = new PcapFileInputStreamGenerator(pipe, f1);
		
		while(!pipe.isClosed()) {
			generator.process();
		}
		
		Assert.assertEquals(0x01, pipe.retireve().byteValue());
		Assert.assertEquals(0x02, pipe.retireve().byteValue());
		Assert.assertEquals(0x03, pipe.retireve().byteValue());
		
	}
	
	@Test
	public void twoFilesTest() {

		Pipe<Byte> pipe = new QueuePipe<>();
		
		PcapFileInputStreamGenerator generator = new PcapFileInputStreamGenerator(pipe, getList());
		
		while(!pipe.isClosed()) {
			generator.process();
		}
		
		Assert.assertEquals(0x01, pipe.retireve().byteValue());
		Assert.assertEquals(0x02, pipe.retireve().byteValue());
		Assert.assertEquals(0x03, pipe.retireve().byteValue());
		
		Assert.assertTrue(pipe.canRetrieve());
		
		Assert.assertEquals(0x0A, pipe.retireve().byteValue());
		Assert.assertEquals(0x0B, pipe.retireve().byteValue());
		Assert.assertEquals(0x0C, pipe.retireve().byteValue());
		
	}
	
	private List<File> getList(){
		return Arrays.asList(new File[] {
				f1,
				f2
		});
	}
	
}
