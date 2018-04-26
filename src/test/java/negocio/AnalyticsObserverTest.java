package negocio;

import org.junit.Before;
import org.junit.Test;

import model.Capture;
import model.Package;
import model.TimeStamp;
import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import pipeAndFilter.Pipe;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class AnalyticsObserverTest {

	private Capture cap1;
	private Capture cap2;
	private Capture cap3;
	
	private Sniffer s1;
	private Sniffer s2;
	private Sniffer s3;
	
	@Before
	public void before() {
		
		MacAddress m1 = new MacAddress(new byte[] {(byte)0x0f});
		MacAddress m2 = new MacAddress(new byte[] {(byte)0x0e});
		MacAddress m3 = new MacAddress(new byte[] {(byte)0x0d});
		
		Package p1 = new Package(new TimeStamp(0), m1);
		Package p2 = new Package(new TimeStamp(1000), m1);
		Package p3 = new Package(new TimeStamp(2000), m1);

		Package p4 = new Package(new TimeStamp(3000), m2);
		Package p5 = new Package(new TimeStamp(4000), m2);
		Package p6 = new Package(new TimeStamp(5000), m2);

		Package p7 = new Package(new TimeStamp(6000), m3);
		Package p8 = new Package(new TimeStamp(7000), m3);
		Package p9 = new Package(new TimeStamp(8000), m3);
		
		Device d1 = new Device(m1);
		Device d2 = new Device(m2);
		Device d3 = new Device(m3);
		
		s1 = new Sniffer(d1, 10);
		s2 = new Sniffer(d2, 11);
		s3 = new Sniffer(d3, 12);
		
		cap1 = new Capture(s1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		cap2 = new Capture(s2, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		cap3 = new Capture(s3, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		
		cap1.addPackages(p1);
		cap1.addPackages(p2);
		cap1.addPackages(p3);
		
		cap2.addPackages(p4);
		cap2.addPackages(p5);
		cap2.addPackages(p6);

		cap3.addPackages(p7);
		cap3.addPackages(p8);
		cap3.addPackages(p9);
	}
	
	@Test
	public void test() {
		
		Pipe<Capture> pipe = mock(Pipe.class);
		when(pipe.canRetrieve())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false);
		
		when(pipe.retireve()).thenReturn(cap1).thenReturn(cap2).thenReturn(cap3);
		
		AnalyticsObserver o = new AnalyticsObserver();
		
		SnifferDetectedMac sink = new SnifferDetectedMac(pipe, s1.getDevice().getMac());
		
		
		sink.addObserver(o);
		
		while(pipe.canRetrieve()) {
			sink.process();
		}
	}
	
}
