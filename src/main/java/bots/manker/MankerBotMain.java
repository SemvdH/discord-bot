package bots.manker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import bots.manker.commands.*;
import bots.Command;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * BotMain
 */
public class MankerBotMain extends ListenerAdapter {

    private static final String COMMAND_PREFIX = "!manker";
    private static final String BOT_NAME = "xXx_NoobSlayer69_xXx";

    private List<Command> commandList;

    public void init() throws LoginException, IOException{
        this.commandList = new ArrayList<>();
        this.commandList.add(new FourTwentyCommand());
        this.commandList.add(new HelpCommand());
        this.commandList.add(new MemeCommand());
        this.commandList.add(new SearchCommand());
        this.commandList.add(new YeetCommand());

        for (Command command : this.commandList) {
            command.setCommandPrefix(COMMAND_PREFIX);
        }

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = getTokenFromFile();
        builder.setToken(token);
        builder.addEventListeners(new MankerBotMain());
        builder.setActivity(Activity.playing("with anime tiddies keyword = !manker"));
        builder.build();
    }

    private String getTokenFromFile() throws IOException {
        File file = new File("token");
        if (!file.exists()) throw new NullPointerException("Token file cannot be found!");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.readLine().trim();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (this.isBot(event.getAuthor())) {
            return;
        }

        this.handleCommand(event);
    }

    /**
     * Handle command
     * @param event event
     */
    private void handleCommand(MessageReceivedEvent event) {
        for (Command command : this.commandList) {
            command.setEvent(event);
            if (command.isValid()) {
                command.execute();

                return;
            }
        }

        Command helpCommand = this.getCommand(HelpCommand.COMMAND_NAME);

        if (helpCommand != null) {
            helpCommand.execute();
        }
    }

    /**
     * Get command by command name
     * @param commandName name of command
     * @return command
     */
    private Command getCommand(String commandName) {
        for (Command command : this.commandList) {
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
    private boolean isBot(User user) {
        return user.getName().equals(BOT_NAME);
    }
}