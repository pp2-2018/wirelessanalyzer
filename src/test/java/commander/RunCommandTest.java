package commander;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RunCommandTest extends JCommanderTest {




    @Test
    public void testRun() {
       String[] args = {"run", "Filtro"};

        RunCommand subject = new RunCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("run"));
        assertThat(subject.getFields().toString(), equalTo("[Filtro]"));
    }

    @Test(expected = ParameterException.class)
    public void testParametersException(){
        String[] args = {"run"};
        RunCommand subject = new RunCommand();
        JCommander jCommander = buildAndParse(args, subject);

    }


}