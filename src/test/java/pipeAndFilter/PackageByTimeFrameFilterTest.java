package pipeAndFilter;

import model.Package;
import model.TimeFrame;
import model.TimeStamp;
import model.device.MacAddress;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.filters.rawPackageFilter.PackageByTimeFrameFilter;
import pipeAndFilter.impl.PipeSystem;
import pipeAndFilter.impl.QueuePipe;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class PackageByTimeFrameFilterTest {

    TimeFrame timeFrame;
    LocalDateTime inicio;
    LocalDateTime fin;
    Package packageA;
    @Before
    public void setUp(){
        inicio = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
        fin = LocalDateTime.of(2015, Month.JANUARY, 1, 10, 10, 30);

        timeFrame = new TimeFrame(inicio, fin);

        byte b1[] = {  0x4a, 0x08, 0x7f, 0x41, 0x12, 0x47 };	//mac addresses
        MacAddress addra = new MacAddress(b1);
        packageA = new Package(new TimeStamp(inicio));				//paquetes
        packageA.setMacAddress(addra);


    }

    @Test
    public void test() {

        PipeSystem pipeSystem = new PipeSystem();

        Pipe<Package> pipe1 = new QueuePipe<>();
        Pipe<Package> pipe2 = new QueuePipe<>();

        pipeSystem.addPipe(pipe1);
        pipeSystem.addPipe(pipe2);

        List<Processable> procesabbles = new ArrayList<>();


        PackageByTimeFrameFilter packageByTimeFrameFilter = new PackageByTimeFrameFilter
                (pipe1,pipe2,timeFrame);


        procesabbles.add(packageByTimeFrameFilter);
        pipe1.accept(packageA);
        packageByTimeFrameFilter.process();
        Assert.assertEquals(packageA,pipe2.retireve());


    }


}