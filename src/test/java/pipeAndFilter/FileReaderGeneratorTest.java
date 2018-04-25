package pipeAndFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.RawPackage;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.filters.printSink.PrintSink;
import pipeAndFilter.filters.rawPackageFilter.RawPackage32BytesFilter;
import pipeAndFilter.impl.PipeSystem;
import pipeAndFilter.impl.QueuePipe;

public class FileReaderGeneratorTest {

	
	@Test
	public void test() {
		
		PipeSystem pipeSystem = new PipeSystem();
		
		Pipe<byte[]> pipe1 = new QueuePipe<>();
		Pipe<RawPackage> pipe2 = new QueuePipe<>();
		
		pipeSystem.addPipe(pipe1);
		pipeSystem.addPipe(pipe2);
		
		List<Processable> procesabbles = new ArrayList<>();
		
		PcapFileInputStreamGenerator generator = new PcapFileInputStreamGenerator(pipe1, Arrays.asList(
				new File[]{new File(".#test-files#three-32bytes-pacages.pcap".replaceAll("\\#", "\\" + File.separator))}));
		RawPackage32BytesFilter filter1 = new RawPackage32BytesFilter(pipe1, pipe2);
		
		PrintSink<RawPackage> sink = new PrintSink<RawPackage>(pipe2, p -> p.toString());

		procesabbles.add(generator);
		procesabbles.add(filter1);
		procesabbles.add(sink);
		
		while(pipeSystem.canRetrieveForSomeone()) {
			for (Processable processable : procesabbles) {
				processable.process();
			}
		}
		
	}
	
}
