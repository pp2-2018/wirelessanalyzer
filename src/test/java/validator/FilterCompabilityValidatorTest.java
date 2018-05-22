package validator;

import model.Package;
import model.RawPackage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pipeAndFilter.Pipe;
import pipeAndFilter.SimpleFilter;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.filters.rawPackageFilter.PackageByMacAddressFilter;
import pipeAndFilter.filters.rawPackageFilter.RawPackageFilter;
import pipeAndFilter.impl.QueuePipe;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;
public class FilterCompabilityValidatorTest {

    private FilterCompabilityValidator filterIOValidator;

    @Before
    public void setUp() throws Exception {
        FilterTypeValidator filterTypeValidator = new FilterTypeValidator(SimpleFilter.class);
        filterIOValidator = new FilterCompabilityValidator();
    }

    @Test
    public void ca1(){

        assertTrue(filterIOValidator.validateCompability(getRawPackageFilter(), getPackageBuilder()));

    }

    @Test
    public void ca2(){

        assertFalse(filterIOValidator.validateCompability(getPackageByMacAddressFilter(),getPackageBuilder()));

    }

    @Test
    @Ignore
    public void ca3(){

        assertTrue(filterIOValidator.validateCompability(getPcapStreamGenerator(), getRawPackageFilter()));

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