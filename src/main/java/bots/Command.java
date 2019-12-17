package bots;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

abstract public class Command {
    protected MessageReceivedEvent event;
    protected String commandPrefix;

    public Command() {
        this.event = null;
        this.commandPrefix = null;
    }

    public void setEvent(MessageReceivedEvent event) {
        this.event = event;
    }

    public void setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
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
        return message.getContentRaw().contains(this.commandPrefix);
    }

    protected String parseCommand(Message message) {
        return message.getContentRaw().substring(this.commandPrefix.length()).trim();
    }
}