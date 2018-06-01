package commander;

import java.util.List;

public interface Command extends Runnable {

     List<String> getFields();
     boolean isHelp();
     String getCommandName();
     String getSuccessMessage();

}
