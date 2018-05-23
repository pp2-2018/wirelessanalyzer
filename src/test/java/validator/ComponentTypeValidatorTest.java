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

public class ComponentTypeValidatorTest {

    private ComponentTypeValidator componentTypeValidator;

    @Test
    public void testSink(){
        componentTypeValidator = new ComponentTypeValidator(SinkImpl.class);
        Processable sink = getSink();

        assertTrue(componentTypeValidator.validate(sink));

    }

    @Test
    public void testFilter(){
        componentTypeValidator = new ComponentTypeValidator(SimpleFilterImpl.class);
        Processable filter = getFilter();

        assertTrue(componentTypeValidator.validate(filter));

    }

    @Test
    public void testGenerator(){
        componentTypeValidator = new ComponentTypeValidator(GeneratorImpl.class);
        Processable generator = getGenerator();

        assertTrue(componentTypeValidator.validate(generator));

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