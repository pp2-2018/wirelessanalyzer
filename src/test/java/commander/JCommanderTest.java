package commander;

import com.beust.jcommander.JCommander;

public class JCommanderTest {

    protected JCommander buildAndParse(String[] args, Object command) {
        JCommander jCommander = new JCommander();
        jCommander.addCommand(command);

        jCommander.parse(args);
        return jCommander;
    }
}
