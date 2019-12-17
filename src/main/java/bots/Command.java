package bots;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

abstract public class Command {
    protected Settings settings;
    protected MessageReceivedEvent event;
    protected String commandPrefix;

    public Command(Settings settings) {
        this.settings = settings;
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

        String command = this.parseCommand(this.event.getMessage());

        return this.getCommandName().equals(command);
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
        String parameter = this.event.getMessage().getContentRaw().trim().substring(this.commandPrefix.length() + 1 + this.getCommandName().length());

        return parameter;
    }

    protected boolean isCommand(Message message) {
        return message.getContentRaw().trim().startsWith(this.commandPrefix);
    }

    protected String parseCommand(Message message) {
        String messageString = message.getContentRaw().trim();

        if (messageString.length() < this.commandPrefix.length() + this.getCommandName().length() + 1) {
            return "";
        }

        return messageString.substring(
                this.commandPrefix.length(),
                1 + this.commandPrefix.length() + this.getCommandName().length()
        ).trim();
    }
}
