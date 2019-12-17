package bots.manker.commands;

import bots.Command;
import bots.Settings;

import java.util.Random;

public class ChooseCommand extends Command {
    public static final String COMMAND_NAME = "choose";

    public ChooseCommand(Settings settings) {
        super(settings);
    }

    @Override
    public void execute() {
        Random random = new Random();

        String[] options = this.parseParameters();

        if (options.length == 0) {
            return;
        }

        this.event.getChannel().sendMessage(options[random.nextInt(options.length)]).queue();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
