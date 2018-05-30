package validator;

import org.junit.Test;
import pipeAndFilter.Processable;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.filters.rawPackageFilter.RawPackageFilter;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

import java.io.File;

import static org.junit.Assert.*;

public class FilterTypeValidatorTest {

    private FilterTypeValidator filterTypeValidator;

    @Test
    public void testSink(){
        filterTypeValidator = new FilterTypeValidator();
        Processable sink = getSink();

        assertTrue(filterTypeValidator.isSink(sink));

    }

    @Test
    public void testFilter(){
        filterTypeValidator = new FilterTypeValidator();
        Processable filter = getFilter();

        assertTrue(filterTypeValidator.isFilter(filter));

    }

    @Test
    public void testGenerator(){
        filterTypeValidator = new FilterTypeValidator();
        Processable generator = getGenerator();

        assertTrue(filterTypeValidator.isGenerator(generator));

    }

    private Processable getGenerator() {
        return new PcapFileInputStreamGenerator(null, new File("test-files/wireless.pcap"));
    }

    private Processable getSink() {
        return new SnifferDetectedMac(null, null);
    }

    private Processable getFilter() {
        return new RawPackageFilter(null, null);
    }

}