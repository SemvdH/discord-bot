package bots.manker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import bots.manker.commands.*;
import bots.manker.commands.Command;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
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
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = getTokenFromFile();
        builder.setToken(token);
        builder.addEventListeners(new MankerBotMain());
        builder.setActivity(Activity.playing("with anime tiddies keyword = !manker"));
        builder.build();

        this.commandList = new ArrayList<>();
    }

    private String getTokenFromFile() throws IOException {
        File file = new File("token");
        if (!file.exists()) throw new NullPointerException("Token file cannot be found!");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.readLine().trim();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();

        System.out.printf("We received a message from: %s : %s", author.getName(), message.getContentDisplay());

        if (this.isBot(author)) {
            return;
        }

        this.handleCommand(event);
    }

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

    private Command getCommand(String name) {
        for (Command command : this.commandList) {
            if (command.getCommandName().equals(name)) {
                return command;
            }
        }

        return null;
    }

    private boolean isBot(User user) {
        return user.getName().equals(BOT_NAME);
    }
}