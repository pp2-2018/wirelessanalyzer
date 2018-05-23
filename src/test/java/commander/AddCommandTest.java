package commander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class AddCommandTest extends JCommanderTest {

    @Test(expected = ParameterException.class)
    public void testParametersException(){
        String[] args = {"add", "-name", "Filter"};
        AddCommand subject = new AddCommand();
        buildAndParse(args, subject);

    }

    @Test
    public void testFilterFields() {
        String[] args = {"add", "-name", "Filtros", "FilterA", "FilterB"};
        AddCommand subject = new AddCommand();
        JCommander jCommander = buildAndParse(args, subject);
        assertThat(jCommander.getParsedCommand(), equalTo("add"));
        assertThat(subject.getFilterName(), equalTo("Filtros"));
        assertEquals(subject.getFields().toString(),"[FilterA, FilterB]");
}


}