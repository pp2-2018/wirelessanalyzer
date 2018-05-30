package commander;

import com.beust.jcommander.JCommander;
import pipeAndFilter.registry.CompoundFilterRegistry;
import validator.SyntaxValidator;

import java.util.List;
import java.util.stream.Collectors;

public class WirelessAnalyzer {
    private final JCommander jCommander;

    public WirelessAnalyzer() {
        this.jCommander = buildJCommander();
    }

    private void run(String[] args) {

        if (args.length > 0 && args[0].equals("help")) {
            jCommander.usage();
            return;
        }
        jCommander.parse(args);
        String parsedCommand = jCommander.getParsedCommand();
        List<Object> commands;
        if (parsedCommand != null)
            commands = jCommander.getCommands().get(parsedCommand).getObjects();

        else {
            jCommander.usage();
            return;
        }

        for (Object object : commands) {


           Command Command = (Command) object;
            if (Command.isHelp())
                jCommander.usage(Command.getCommandName());

             else {

                if (checkSyntax(Command)) {
                    try{
                    Command.run();
                        System.out.println(Command.getSuccessMessage());
                    }
                    catch (Exception e){
                        System.out.println("Error:");
                        e.printStackTrace();
                    }

                }

            }
        }

    }


    private static boolean checkSyntax(Command Command) {
        List<String> filtered = Command.getFields().stream().filter(new SyntaxValidator().negate())
                .collect(Collectors.toList());
        if (filtered.size() > 0) {
            System.err.print("Filter Syntax Error: " + filtered.get(0));
            filtered.remove(filtered.get(0));
            for (String malformedFilter :
                    filtered) {
                System.err.print(", " + malformedFilter);
            }
            System.out.println();
            return false;
        }
        return true;
    }


    private JCommander buildJCommander() {
        JCommander jCommander = new JCommander();
        jCommander.addCommand(new AddCommand());
        jCommander.addCommand(new RunCommand());
        jCommander.addCommand(new RemoveCommand());
        jCommander.setProgramName("WirelessAnalyzer");
        return jCommander;
    }


    public static void main(String[] args) {
        WirelessAnalyzer crudWizardApp = new WirelessAnalyzer();
        try {
            crudWizardApp.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



