package pipeAndFilter;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Capture;
import model.Package;
import model.TimeFrame;
import model.TimeStamp;
import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import pipeAndFilter.impl.PipeSystem;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.sink.MacAddressesDetectedBySniffer.MacAddressesDetectedBySniffer;

public class MacAddressesDetectedBySnifferTes {
	
	TimeFrame timeFrame;
	Package package1;
	Sniffer a;
	ArrayList<MacAddress> macs;
	Capture captura;
	MacAddress addrd;
	
	@Before
	public void setUp(){
		
		LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2014, Month.FEBRUARY, 1, 10, 10, 30);
        
		timeFrame = new TimeFrame(start, end);
		
		byte b4[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x09 };		//mac
    	addrd = new MacAddress(b4);
    	
    	package1 = new Package(new TimeStamp(start));			//paquete
        package1.setMacAddress(addrd);
        
        
        byte d1[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x01 }; 	//sniffer
    	MacAddress m1 = new MacAddress(d1);
    	Device dev1 = new Device(m1);
    	a = new Sniffer(dev1, 00000);
        
        captura = new Capture(a, start, end);			//captura
        captura.addPackages(package1);
        
        macs = new ArrayList<MacAddress>();
        macs.add(addrd);
	}


	@Test
	public void test() {
		
		PipeSystem pipeSystem = new PipeSystem();
		Pipe<Capture> pipe1 = new QueuePipe<>();
        
        pipeSystem.addPipe(pipe1);
        
        List<Processable> procesabbles = new ArrayList<>();
        
        MacAddressesDetectedBySniffer macsBySniffer = new MacAddressesDetectedBySniffer
                (pipe1,a);
        
        pipe1.accept(captura);
        
        macsBySniffer.process();
        
        Assert.assertEquals(macs, macsBySniffer.getMacs());
        
	}

}
