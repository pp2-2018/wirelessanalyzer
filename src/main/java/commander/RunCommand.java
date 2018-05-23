package commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import pipeAndFilter.impl.FilterSystem;
import pipeAndFilter.registry.CompoundFilterRegistry;

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
    	
    	CompoundFilterRegistry registry = CompoundFilterRegistry.getInstance();
    	
    	StringBuilder compoundFilter = new StringBuilder();

        for (int i = 0; i < fields.size(); i++) {
            if (i < fields.size() - 1)
                compoundFilter.append(fields.get(i)).append(" ");
            else {
                compoundFilter.append(fields.get(i));
            }
        }
    	
    	FilterSystem filters = registry.getFilterInstance(compoundFilter.toString());

    	System.out.println(filters);
    	
    }
}