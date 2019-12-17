package bots.manker.commands;

public class YeetCommand extends Command {
    public static final String COMMAND_NAME = "yeet";

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
