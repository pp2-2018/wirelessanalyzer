package commander;

import exceptions.NotRegisteredFilter;
import org.junit.Test;
import pipeAndFilter.registry.CompoundFilterRegistry;

import static org.junit.Assert.*;

public class WirelessAnalyzerTest {


    @Test
    public void add() {
        String[] parameters = {"add", "-name", "test", "filter"};
        WirelessAnalyzer.main(parameters);
        assertEquals("filter",CompoundFilterRegistry.getInstance().get("test"));
    }
    @Test
    public void run() {
        String[] parameters = {"run", "test", "filter"};
        WirelessAnalyzer.main(parameters);
    }
    @Test(expected = NotRegisteredFilter.class)
    public void remove() {
        CompoundFilterRegistry.getInstance("test").set("filterName","FilterA FilterB FilterC");
        String[] parameters = {"remove", "filterName"};
        WirelessAnalyzer.main(parameters);
        CompoundFilterRegistry.getInstance().get("filterName");

    }
}