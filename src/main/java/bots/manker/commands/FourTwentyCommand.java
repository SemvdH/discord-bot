package bots.manker.commands;

import bots.Command;
import bots.Settings;

public class FourTwentyCommand extends Command {
    public static final String COMMAND_NAME = "420";

    public FourTwentyCommand(Settings settings) {
        super(settings);
    }

    @Override
    public void execute() {
        String res = "";
        for (int i = 0; i < 69; i++) {
            res += "MANKER ";
        }

        event.getChannel().sendMessage(res).queue();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
