package commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandNames = {"run"}, commandDescription = "|| Constructs and executes filters ||")
public class RunCommand implements Command {

    @Parameter(description = "FilterA FilterB FilterC ", required = true)
    private List<String> fields = new ArrayList<>();

    @Parameter(names = "-help", description = "See usages", help = true)
    private boolean help = false;

    public List<String> getFields() {
            return fields;
    }


    public boolean isHelp() {
        return help;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}