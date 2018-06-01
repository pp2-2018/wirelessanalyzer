package pipeAndFilter.filters.rawPackageFilter;

import exceptions.IllegalPipeAndFilterCompositionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.Pipe;
import pipeAndFilter.Processable;
import pipeAndFilter.SimpleFilter;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.impl.QueuePipe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FiltersCompatibilityFilterTest{

    private SimpleFilter compatibilityFilter;

    Pipe<Processable> inputPipe;
    Pipe<Processable> outputPipe;

    @Before
    public void setUp(){
        outputPipe = new QueuePipe<>();
        inputPipe = getValidSequenceOfFilters();
        compatibilityFilter = new FiltersCompatibilityFilter(inputPipe, outputPipe);
    }

    //FIXME Este filter no suelta todos los Filtros
    @Test //TODO Incluir los asserts
    public void testValidFilter(){

        while(!inputPipe.isEmpty())
            compatibilityFilter.process();
    }

    @Test(expected = IllegalPipeAndFilterCompositionException.class)
    public void testInvalidFilter(){
        addIncompatibilityFilterToPipe();

        while(!inputPipe.isEmpty())
            compatibilityFilter.process();
    }

    @After
    public void tearDown(){
        compatibilityFilter = null;
        inputPipe = null;
        outputPipe = null;
    }


    private Pipe<Processable> getValidSequenceOfFilters(){
        List<Processable> filters = new ArrayList<>();
        Pipe<Processable> processablePipe = new QueuePipe<>();

        filters.add(new PcapFileInputStreamGenerator(null,
                Arrays.asList(new File[]{new File("test-files/wireless.pcap")})));
        filters.add(new RawPackageFilter(null, null));
        filters.add(new PackageBuilderNormalizer(null, null));
        filters.add(new PackageByMacAddressFilter(null, null,null));

        filters.forEach(filter -> processablePipe.accept(filter));

        return processablePipe;
    }

    private void addIncompatibilityFilterToPipe(){
        inputPipe.accept(new RawPackageFilter(null, null));
    }
}