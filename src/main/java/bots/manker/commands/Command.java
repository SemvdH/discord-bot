package bots.manker.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

abstract public class Command {
    private static final String COMMAND_PREFIX = "!manker";

    protected MessageReceivedEvent event;

    public Command() {
        this.event = null;
    }

    public void setEvent(MessageReceivedEvent event) {
        this.event = event;
    }

    public abstract void execute();
    public abstract String getCommandName();

    public boolean isValid() {
        if (!this.isCommand(this.event.getMessage())) {
            return false;
        }

        return this.getCommandName().equals(this.parseCommand(this.event.getMessage()));
    }

    protected String[] parseParameters() {
        String parameterString = this.parseParameter();
        String[] parameters = parameterString.split(",");

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].trim();
        }

        return parameters;
    }

    protected String parseParameter() {
        return parseCommand(this.event.getMessage()).substring(this.getCommandName().length());
    }

    protected boolean isCommand(Message message) {
        return message.getContentRaw().contains(COMMAND_PREFIX);
    }

    protected String parseCommand(Message message) {
        return message.getContentRaw().substring(COMMAND_PREFIX.length()).trim();
    }
}
