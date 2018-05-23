package validator;

import model.Package;
import model.RawPackage;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Pipe;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.filters.rawPackageFilter.PackageByMacAddressFilter;
import pipeAndFilter.filters.rawPackageFilter.RawPackageFilter;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.sink.MacAddressesDetectedBySniffer.MacAddressesDetectedBySniffer;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;
public class FilterCompabilityValidatorTest {

    private FilterCompabilityValidator compabilityValidator;

    @Before
    public void setUp() throws Exception {
        compabilityValidator = new FilterCompabilityValidator();
    }

    @Test //ca1
    public void filterAndFilter(){
        assertTrue(compabilityValidator.validateCompability(getRawPackageFilter(), getPackageBuilder()));
    }

    @Test //ca2
    public void filterAndFilterFalse(){
        assertFalse(compabilityValidator.validateCompability(getPackageByMacAddressFilter(),getPackageBuilder()));
    }

    @Test //ca3
    public void generatorAndFilter(){
        assertTrue(compabilityValidator.validateCompability(getPcapStreamGenerator(), getRawPackageFilter()));
    }

    @Test(expected = IllegalArgumentException.class) //ca4
    public void filterAndGenerator(){
        assertFalse(compabilityValidator.validateCompability(getRawPackageFilter(), getPcapStreamGenerator()));
    }

    @Test //ca5
    public void filterAndSink(){
        //FIXME No tenemos clases para testear esto
    }

    @Test(expected = IllegalArgumentException.class) //ca6
    public void sinkAndFilter(){
        assertTrue(compabilityValidator.validateCompability(getMacAddressDetectedBySnifferSink(), getRawPackageFilter()));
    }

    @Test //ca7
    public void generatorAndSink(){
        //TODO No tenemos clases para testear esto
    }

    private MacAddressesDetectedBySniffer getMacAddressDetectedBySnifferSink(){
        return new MacAddressesDetectedBySniffer(null, null);
    }

    private PackageByMacAddressFilter getPackageByMacAddressFilter() {
        Pipe<Package> inputPackage = new QueuePipe<>();
        Pipe<Package> outputPackage = new QueuePipe<>();

        return new PackageByMacAddressFilter(inputPackage, outputPackage, null);
    }

    private RawPackageFilter getRawPackageFilter(){

        Pipe<Byte> bytePipe = new QueuePipe<>();
        Pipe<RawPackage> rawPackagePipe = new QueuePipe<>();

        return new RawPackageFilter(bytePipe, rawPackagePipe);
    }

    private PackageBuilderNormalizer getPackageBuilder(){
        Pipe<RawPackage> rawPackagePipe = new QueuePipe<>();
        Pipe<Package> packagePipe = new QueuePipe<>();

        return new PackageBuilderNormalizer(rawPackagePipe, packagePipe);

    }

    private PcapFileInputStreamGenerator getPcapStreamGenerator(){

        return new PcapFileInputStreamGenerator(null,
                Arrays.asList(new File[]{new File("test-files/wireless.pcap")}));

    }

}