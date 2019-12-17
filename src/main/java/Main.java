import java.io.File;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.parser.ParseException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import bots.manker.*;
import bots.manker.functionalities.json.JSONFunctions;
import bots.manker.functionalities.playerstats.Player;

public class Main extends ListenerAdapter {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws LoginException, IOException {
        MankerBotMain mankerBot = new MankerBotMain();
        mankerBot.init();
    }

}
