package bots.manker;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * BotMain
 */
public class MankerBotMain extends ListenerAdapter {

    public void init() throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NjIxODEwMjk4NzgwNzc4NDk2.Xfejbw.G";
        token += "GAyqHH8IK5ruWKigv";
        token += "1KHuS9grU";
        builder.setToken(token);
        builder.addEventListeners(new MankerBotMain());
        builder.setActivity(Activity.playing("with anime tiddies"));
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        System.out.println("We received a message from: " + event.getAuthor().getName() + " : "
                + event.getMessage().getContentDisplay());
        if (!event.getAuthor().getName().equals("xXx_NoobSlayer69_xXx")) {

            if (event.getMessage().getContentRaw().equals("!manker")) {
                // call queue(), otherwise our message will never be sent
                event.getChannel().sendMessage("manker?").queue();
            } else if (event.getMessage().getContentRaw().equals("!manker meme")) {

                Command.sendMeme(event);

            } else if (event.getMessage().getContentRaw().equals("!manker yeet")) {
                String name = event.getAuthor().getName();
                event.getChannel().sendMessage("yeet " + name).queue();

            } else if (event.getMessage().getContentRaw().equals("!manker help")) {

                Command.sendHelpInfo(event);

            } else if (event.getMessage().getContentRaw().equals("!manker420")) {
                String res = "";
                for (int i = 0; i < 69; i++) {
                    res += "MANKER ";
                }

                event.getChannel().sendMessage(res).queue();
            } else if (event.getMessage().getContentRaw().startsWith("!manker search")) {
                String text = event.getMessage().getContentRaw();
                text = text.substring(14);
                text = text.trim();
                System.out.println("the received tag was: " + text);
                try {
                    event.getChannel().sendMessage(GoogleSearch.search(text)).queue();
                } catch (IOException e) {
                    e.printStackTrace();
                    event.getChannel().sendMessage("There was an error while looking for an image!").queue();
                }

            } else {
                if (event.getMessage().getContentRaw().startsWith("!manker")) {
                    event.getChannel().sendMessage("What? I didn't understand you!\nLet me help you with the commands:").queue();
                    Command.sendHelpInfo(event);
                }
            }


        }
    }
    
}