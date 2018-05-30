package commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import pipeAndFilter.registry.CompoundFilterRegistry;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandNames = {"remove"}, commandDescription = "|| Removes compound filters ||")
public class RemoveCommand implements Command{

    @Parameter(description = "CompoundFilterA CompoundFilterB CompoundFilterC ", required = true)
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
    public String getCommandName() {
        return "remove";
    }

    @Override
    public String getSuccessMessage() {
        return "Removed: " + getFields();
    }

    @Override
    public void run() {
        if(fields.size()>0) {
            fields.forEach(e->CompoundFilterRegistry.getInstance().remove(e));
        }
    }
}