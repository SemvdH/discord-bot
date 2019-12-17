import bots.Bot;
import bots.Settings;
import bots.manker.MankerBot;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        try {
            Settings settings = new Settings(Dotenv.configure().load());

            Bot mankerBot = new MankerBot(settings);

            buildJDAConnection(settings, mankerBot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buildJDAConnection(Settings settings, Bot bot) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(settings.getToken());
        builder.addEventListeners(bot);
        builder.setActivity(Activity.playing(settings.getActivity()));
        builder.build();
    }
}
