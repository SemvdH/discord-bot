package bots.manker;

import bots.Bot;
import bots.Command;
import bots.Settings;
import bots.manker.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MankerBot extends Bot {
    private MessageAnalyzer analyzer;

    public MankerBot(Settings settings) {
        super(settings);

        this.commandList.add(new FourTwentyCommand(settings));
        this.commandList.add(new HelpCommand(settings));
        this.commandList.add(new MemeCommand(settings));
        this.commandList.add(new SearchCommand(settings));
        this.commandList.add(new YeetCommand(settings));
        this.commandList.add(new ChooseCommand(settings));

        for (Command command : commandList) {
            command.setCommandPrefix(settings.getCommandPrefix());
        }

        this.analyzer = new MessageAnalyzer();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (!this.isBot(event.getAuthor())) {
            this.analyzer.checkSpecialCases(event);
        }

        if (!this.isBot(event.getAuthor()) && this.analyzer.hasMeanWords(event)) {
            event.getMessage().delete().queue();

            String message = this.analyzer.analyzeAndReplaceMeanWords(event);

            event.getChannel().sendMessage(
                    "> Your message has been Jolified.\n" +
                    "```" + message + "```- *" + event.getAuthor().getName() +
                    "* at " + event.getMessage().getTimeCreated().atZoneSameInstant(ZoneId.of("Europe/Amsterdam")).format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            ).queue();
        }
    }
}
