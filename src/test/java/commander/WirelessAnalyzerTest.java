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
        assertEquals("filter",CompoundFilterRegistry.getInstance().getFilterString("test"));
        parameters = new String[]{"add", "-help"};
        WirelessAnalyzer.main(parameters);
    }
    @Test
    public void run() {
        String[] parameters = {"run", "test"};
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
        CompoundFilterRegistry.getInstance().get("filterName");

    }
}