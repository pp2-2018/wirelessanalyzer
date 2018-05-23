package commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class RemoveCommandTest extends JCommanderTest {

    @Test(expected = ParameterException.class)
    public void testParametersException(){
        String[] args = {"remove", "Filter"};
        AddCommand subject = new AddCommand();
        buildAndParse(args, subject);

    }

    @Test
    public void testFilterFields() {
        String[] args = {"remove", "FilterA", "FilterB"};
        RemoveCommand subject = new RemoveCommand();
        JCommander jCommander = buildAndParse(args, subject);
        assertThat(jCommander.getParsedCommand(), equalTo("remove"));
        assertEquals(subject.getFields().toString(),"[FilterA, FilterB]");
}


}