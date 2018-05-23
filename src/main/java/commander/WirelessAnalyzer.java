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

            if(args.length>0&&args[0].equals("help")){
                jCommander.usage();
                return;
            }
            jCommander.parse(args);
            String parsedCommand = jCommander.getParsedCommand();
            List<Object> commands;
            if(parsedCommand!=null)
               commands = jCommander.getCommands().get(parsedCommand).getObjects();

            else {
                jCommander.usage();
                return;
            }

            for (Object command : commands) {
                if (command instanceof AddCommand) {
                    AddCommand addCommand = (AddCommand) command;
                    if (addCommand.isHelp()) {
                        jCommander.usage("add");

                    }
                    else {

                        if (checkSyntax(addCommand)) {
                            addCommand.run();
                            System.out.println("Saved " + addCommand.getFilterName() + ": " + addCommand.getFields());
                        }

                    }
                    return;
                }
                else if (command instanceof RunCommand) {
                    RunCommand runCommand = (RunCommand) command;
                    if (runCommand.isHelp()) {
                        jCommander.usage("run");

                    }
                    else{


                        if(checkSyntax(runCommand)) {
                            runCommand.run();
                            System.out.println("Running: " + runCommand.getFields());

                        }
                    }
                    return;
                }
                else if (command instanceof RemoveCommand) {
                    RemoveCommand RemoveCommand = (RemoveCommand) command;
                    if (RemoveCommand.isHelp()) {
                        jCommander.usage("remove");

                    }
                    else{
                        RemoveCommand.run();
                        System.out.println("Removing: " + RemoveCommand.getFields());

                    }
                    return;
                }


            }


        }

    private static boolean checkSyntax(Command Command) {
        List<String> filtered = Command.getFields().stream().filter(new SyntaxValidator().negate())
                .collect(Collectors.toList());
        if(filtered.size()>0){
           System.err.print("Filter Syntax Error: "+filtered.get(0));
           filtered.remove(filtered.get(0));
            for (String malformedFilter:
                 filtered) {
                System.err.print(", "+malformedFilter);
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



