package validator;

import model.Package;
import model.RawPackage;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Pipe;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
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


    public RawPackageFilter getRawPackageFilter(){

        Pipe<Byte> bytePipe = new QueuePipe<>();
        Pipe<RawPackage> rawPackagePipe = new QueuePipe<>();

        return new RawPackageFilter(bytePipe, rawPackagePipe);
    }

    public PackageBuilderNormalizer getPackageBuilder(){
        Pipe<RawPackage> rawPackagePipe = new QueuePipe<>();
        Pipe<Package> packagePipe = new QueuePipe<>();

        return new PackageBuilderNormalizer(rawPackagePipe, packagePipe);

    }

}