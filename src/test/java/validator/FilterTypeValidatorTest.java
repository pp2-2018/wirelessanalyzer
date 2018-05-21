package validator;

import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Processable;
import pipeAndFilter.Sink;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.filters.rawPackageFilter.RawPackageFilter;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

import java.io.File;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Filter;

import static org.junit.Assert.*;

public class FilterTypeValidatorTest {

    private FilterTypeValidator filterTypeValidator;

    @Test
    public void testSink(){
        filterTypeValidator = new FilterTypeValidator(SinkImpl.class);
        Processable sink = getSink();

        assertTrue(filterTypeValidator.validate(sink));

    }

    @Test
    public void testFilter(){
        filterTypeValidator = new FilterTypeValidator(SimpleFilterImpl.class);
        Processable filter = getFilter();

        assertTrue(filterTypeValidator.validate(filter));

    }

    @Test
    public void testGenerator(){
        filterTypeValidator = new FilterTypeValidator(GeneratorImpl.class);
        Processable generator = getGenerator();

        assertTrue(filterTypeValidator.validate(generator));

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