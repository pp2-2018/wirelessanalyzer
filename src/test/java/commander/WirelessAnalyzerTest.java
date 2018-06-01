package commander;

import exceptions.NotRegisteredFilter;
import org.junit.Test;
import pipeAndFilter.registry.CompoundFilterRegistry;

import static org.junit.Assert.*;

public class WirelessAnalyzerTest {


    @Test
    public void add() {
        String[] parameters = {"add", "-name", "test", "testGenerator(thisisatest)","testFilter","testSink"};
        WirelessAnalyzer.main(parameters);
        assertEquals("testGenerator(thisisatest) testFilter testSink",CompoundFilterRegistry.getInstance("test").getFilterString("test"));

    }
    @Test
    public void run() {

        String[] parameters = {"add", "-name", "filter", "testGenerator(elfiltropiola"
        		+ ")", "testFilter", "testSink"};
        WirelessAnalyzer.main(parameters);
        parameters = new String[] {"run", "filter"};
        WirelessAnalyzer.main(parameters);

    }
    @Test
    public void runCompoundFilter() {
        add();
        String[] parameters = new String[] {"run", "filter"};
        WirelessAnalyzer.main(parameters);

    }

    @Test
    public void errors() {
        String[] parameters = {"help"};
        WirelessAnalyzer.main(parameters);

    }
    
    @Test
    public void syntax(){
        String[] parameters = new String[]{"run", " filter", "filter ()", "filter"};
        WirelessAnalyzer.main(parameters);
    }


    @Test(expected = NotRegisteredFilter.class)
    public void remove() {
        String[] parameters = new String[]{"remove", "-help"};
        WirelessAnalyzer.main(parameters);
        CompoundFilterRegistry.getInstance("test").set("filterName","FilterA FilterB FilterC");
        parameters = new String[]{"remove", "filterName"};
        WirelessAnalyzer.main(parameters);
        CompoundFilterRegistry.getInstance("test").get("filterName");

    }
}