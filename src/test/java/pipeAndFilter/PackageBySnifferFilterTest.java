package pipeAndFilter;

import org.junit.Assert;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;
import model.Package;
import model.TimeStamp;
import model.device.roles.Sniffer;
import pipeAndFilter.filters.rawPackageFilter.PackageBySnifferFilter;
import pipeAndFilter.impl.QueuePipe;

public class PackageBySnifferFilterTest {

	@Test
	public void test() {
		
		Package p1 = new Package(new TimeStamp(0));
		
		Pipe<Package> input = new QueuePipe<>();
		Pipe<Package> output = new QueuePipe<>();
		Device d = new Device(new MacAddress(new byte[] {0x01}));
		Sniffer s = new Sniffer(d, 0);

		p1.setSniffer(s);
		
		PackageBySnifferFilter filter = new PackageBySnifferFilter(input, output, s);
		
		input.accept(p1);
		
		filter.process();
		
		Assert.assertEquals(output.retireve(), p1);
		
	}
	
	@Test
	public void twoPackagesTest() {
		
		Package p1 = new Package(new TimeStamp(0));
		Package p2 = new Package(new TimeStamp(0));
		
		Pipe<Package> input = new QueuePipe<>();
		Pipe<Package> output = new QueuePipe<>();
		Device d = new Device(new MacAddress(new byte[] {0x01}));
		Sniffer s = new Sniffer(d, 0);
		Device falseDevice = new Device(new MacAddress(new byte[] {0x01}));
		Sniffer falseSniffer = new Sniffer(falseDevice, 0);

		p1.setSniffer(s);
		p2.setSniffer(falseSniffer);
		
		PackageBySnifferFilter filter = new PackageBySnifferFilter(input, output, s);
		
		input.accept(p1);
		
		while(!input.isEmpty()) {
			filter.process();
		}
		
		Assert.assertEquals(output.retireve(), p1);
		Assert.assertTrue(output.isEmpty());
		
	}
	
	
}
