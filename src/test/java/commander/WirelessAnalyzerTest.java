package commander;

import org.junit.Test;
import pipeAndFilter.registry.CompoundFilterRegistry;

import static org.junit.Assert.*;

public class WirelessAnalyzerTest {

    @Test
    public void main() {
        String[] parameters = {"add", "-name", "test", "filter"};
        WirelessAnalyzer.main(parameters);
        assertEquals("filter",CompoundFilterRegistry.getInstance().get("test"));
    }
}