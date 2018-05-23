package commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import pipeAndFilter.registry.CompoundFilterRegistry;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandNames = {"add"}, commandDescription = "|| Saves a new compound filter ||")
public class AddCommand implements Command{

    @Parameter(description = "Name FilterA FilterB FilterC ", required = true)
    private List<String> fields = new ArrayList<>();

    @Parameter(names = {"-name", "-n"}, description = "Name of the filter", required = true)
    private String filter;

    @Parameter(names = "-help", description = "See usages", help = true)
    private boolean help = false;





    public List<String> getFields() {
        return fields;
    }

    String getFilterName() {
        return filter;
    }

    public boolean isHelp() {
        return help;
    }

    @Override
    public void run() {
        if(fields.size()>0) {
            StringBuilder compoundFilter = new StringBuilder();

            for (int i = 0; i < fields.size(); i++) {
                if (i < fields.size() - 1)
                    compoundFilter.append(fields.get(i)).append(" ");
                else {
                    compoundFilter.append(fields.get(i));
                }
            }

            CompoundFilterRegistry.getInstance().set(filter, compoundFilter.toString());
        }
    }
}