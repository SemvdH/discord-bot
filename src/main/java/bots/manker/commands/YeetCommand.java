package bots.manker.commands;

import bots.Command;
import bots.Settings;

public class YeetCommand extends Command {
    public static final String COMMAND_NAME = "yeet";

    public YeetCommand(Settings settings) {
        super(settings);
    }

    @Override
    public void execute() {
        String name = event.getAuthor().getName();
        event.getChannel().sendMessage("yeet " + name).queue();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
