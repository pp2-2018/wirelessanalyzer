package pipeAndFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.RawPackage;
import pipeAndFilter.filters.fileReader.PcapFileReaderGenerator;
import pipeAndFilter.filters.printSink.PrintSink;
import pipeAndFilter.filters.rawPackageFilter.RawPackage32BytesFilter;
import pipeAndFilter.filters.stringToHexFilter.StringToHexFilter;
import pipeAndFilter.impl.PipeSystem;
import pipeAndFilter.impl.QueuePipe;

public class FileReaderGeneratorTest {

	
	@Test
	public void test() {
		
		PipeSystem pipeSystem = new PipeSystem();
		
		Pipe<String> pipe1 = new QueuePipe<>();
		Pipe<byte[]> pipe2 = new QueuePipe<>();
		Pipe<RawPackage> pipe3 = new QueuePipe<>();
		
		pipeSystem.addPipe(pipe1);
		pipeSystem.addPipe(pipe2);
		pipeSystem.addPipe(pipe3);
		
		List<Processable> procesabbles = new ArrayList<>();
		
		PcapFileReaderGenerator generator = new PcapFileReaderGenerator(pipe1, Arrays.asList(new File[]{new File(".\\test-files\\three-32bytes-pacages.pcap")}));
		StringToHexFilter filter1 = new StringToHexFilter(pipe1, pipe2);
		RawPackage32BytesFilter filter2 = new RawPackage32BytesFilter(pipe2, pipe3);
		
		PrintSink<RawPackage> sink = new PrintSink<RawPackage>(pipe3, p -> p.toString());

		procesabbles.add(generator);
		procesabbles.add(filter1);
		procesabbles.add(filter2);
		procesabbles.add(sink);
		
		while(pipeSystem.canRetrieveForSomeone()) {
			for (Processable processable : procesabbles) {
				processable.process();
			}
		}
		
	}
	
}
