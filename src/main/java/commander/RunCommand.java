package commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import exceptions.NotRegisteredFilter;
import pipeAndFilter.Processable;
import pipeAndFilter.impl.FilterSystem;
import pipeAndFilter.registry.CompoundFilterRegistry;
import pipeAndFilter.registry.FilterGlue;
import pipeAndFilter.registry.FilterRegistry;
import validator.SyntaxValidator;

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
    @Override
    public String getCommandName() {
        return "run";
    }

    @Override
    public String getSuccessMessage() {
        return "Running: " + getFields();
    }


    public boolean isHelp() {
        return help;
    }

    @Override
    public void run() {
        CompoundFilterRegistry compoundRegistry = CompoundFilterRegistry.getInstance();
        FilterRegistry registry = FilterRegistry.getInstance();
        List<Processable> toRet = new ArrayList<Processable>();
        SyntaxValidator syntaxValidator= new SyntaxValidator();
        for (String field:fields
             ) {
            try{
                Processable filter = registry.get(field);
                toRet.add(filter);
            }
            catch (NotRegisteredFilter e){
                if(syntaxValidator.hasParameter(field)) {
                    throw new IllegalArgumentException("Compound Filters cannot receive parameters!");
                }
                
                
                ArrayList<Processable> processables =(ArrayList<Processable>) compoundRegistry.get(field);
                toRet.addAll(processables);
             }

            }
            System.out.println(toRet);

            FilterGlue glue = new FilterGlue();
            FilterSystem system = glue.glue(toRet);
            
            system.run();
        }






    	
    }
