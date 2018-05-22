package pipeAndFilter;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Coordinates;
import model.Package;
import model.TimeStamp;
import model.device.ConfigurationMap;
import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.ConfigurationManager;
import pipeAndFilter.Pipe;
import pipeAndFilter.filters.rawPackageFilter.TriangulatorBySniffer;
import pipeAndFilter.impl.QueuePipe;

public class TriangulatorBySnifferTest {
	
	 	Coordinates coords01 ;
	    Coordinates coords02 ;
	    
	    Device dev01;
	    Device dev02;
	    
	    Sniffer ap01;
	    Sniffer ap02;

	    Package packageA;
	    Package packageB;
	    
	    ConfigurationMap configurationMap;
	
	@Before
	public void setUp(){
		
		configurationMap = new ConfigurationMap();
			
		byte [] mac01= {(byte)0XAB,(byte)0XCD,(byte)0XEF,(byte)0XAB,(byte)0XCD,(byte)0XEF};
        byte [] mac02= {(byte)0XAA,(byte)0XBB,(byte)0XCC,(byte)0XDD,(byte)0XEE,(byte)0XFF};
        
        coords01 = new Coordinates(2, 5);
        coords02 = new Coordinates(5, 8);
        
        dev01 = new Device(new MacAddress(mac01));
        dev02 = new Device(new MacAddress(mac02));

        ap01 = new Sniffer(dev01, 100);
        ap02 = new Sniffer(dev02, 150);
        
        configurationMap.register(ap01,coords01);
        configurationMap.register(ap02,coords02);
        
        ap01.setRangeInMeters(3);
        ap02.setRangeInMeters(3);

        packageA = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 30, 0)));
        packageB = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 30, 0)));
        packageA.setSniffer(ap01);
        packageB.setSniffer(ap02);
        
        ConfigurationManager.save(configurationMap);
	}

	@Test
	public void triangularTest() {
		
		Pipe<Package> pipe1 = new QueuePipe<>();
        Pipe<Coordinates> pipe2 = new QueuePipe<>();
		
		TriangulatorBySniffer triangulador = new TriangulatorBySniffer(pipe1, pipe2);
		
		Queue<Package> cola = new LinkedList<Package>();
		cola.add(packageA);
		cola.add(packageB);
		
		Coordinates expected = new Coordinates(3.5, 6.5);
		
		Assert.assertEquals(expected , triangulador.triangular(cola));
	}

}
