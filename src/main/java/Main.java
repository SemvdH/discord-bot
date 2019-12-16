import java.io.IOException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import bots.manker.*;

public class Main extends ListenerAdapter {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws LoginException {
        MankerBotMain mankerBot = new MankerBotMain();
        try {
            mankerBot.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    






}
