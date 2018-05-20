package validator;

import model.Package;
import model.RawPackage;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Pipe;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.rawPackageFilter.PackageByMacAddressFilter;
import pipeAndFilter.filters.rawPackageFilter.RawPackageFilter;
import pipeAndFilter.impl.QueuePipe;

import static org.junit.Assert.*;
public class FilterIOTypeValidatorTest {

    private FilterIOTypeValidator filterIOValidator;

    @Before
    public void setUp() throws Exception {

        filterIOValidator = new FilterIOTypeValidator();
    }

    @Test
    public void ca1(){

        assertTrue(filterIOValidator.validateTypte(getPackageBuilder(),getRawPackageFilter()));

    }

    @Test
    public void ca2(){

        assertFalse(filterIOValidator.validateTypte(getPackageBuilder(), getPackageByMacAddressFilter()));

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

}