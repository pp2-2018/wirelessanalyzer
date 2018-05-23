package commands;

import java.util.List;

public interface Command extends Runnable {

    public List<String> getFields();
    public boolean isHelp();

}
