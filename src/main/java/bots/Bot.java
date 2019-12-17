package bots;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class Bot extends ListenerAdapter {
    protected Settings settings;
    protected List<Command> commandList;

    public Bot(Settings settings) {
        this.settings = settings;
        this.commandList = new ArrayList<>();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (this.isBot(event.getAuthor())) {
            return;
        }

        this.handleCommand(event);
    }

    /**
     * Handle command
     * @param event event
     */
    protected boolean handleCommand(MessageReceivedEvent event) {
        for (Command command : commandList) {
            command.setEvent(event);
            if (command.isValid()) {
                command.execute();

                return true;
            }
        }

        return false;
    }

    /**
     * Get command by command name
     * @param commandName name of command
     * @return command
     */
    protected Command getCommand(String commandName) {
        for (Command command : commandList) {
            if (command.getCommandName().equals(commandName)) {
                return command;
            }
        }

        return null;
    }

    /**
     * Check if user is bot
     * @param user user
     * @return if bot
     */
    protected boolean isBot(User user) {
        return user.getName().equals(this.settings.getBotName());
    }
}
