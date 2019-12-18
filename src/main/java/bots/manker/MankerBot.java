package bots.manker;

import bots.Bot;
import bots.Command;
import bots.Settings;
import bots.manker.commands.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.requests.RestAction;

import javax.annotation.Nonnull;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        if (!event.getAuthor().isBot()) {
            this.cleanup(event);
        }

        if (!this.isBot(event.getAuthor()) && this.analyzer.hasMeanWords(event)) {
            event.getMessage().delete().queue();

            String message = this.analyzer.analyzeAndReplaceMeanWords(event);

            event.getChannel().sendMessage(
                    "> Your message has been Jolified.\n" +
                            "```Markdown\n" + message + "```- *" + event.getAuthor().getName() +
                            "* at " + event.getMessage().getTimeCreated().atZoneSameInstant(ZoneId.of("Europe/Amsterdam")).format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            ).queue();
        }

        if (!this.isBot(event.getAuthor())) {
            this.analyzer.checkSpecialCases(event);
        }
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        if (event.getReaction().getReactionEmote().isEmoji()) {
            if (event.getReaction().getReactionEmote().getAsCodepoints().equals("U+1f346")) {
                event.getTextChannel().addReactionById(event.getMessageIdLong(), "U+1f4a6").queue();
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {
        if (event.getReaction().getReactionEmote().isEmoji()) {
            if (event.getReaction().getReactionEmote().getAsCodepoints().equals("U+1f346")) {
                event.getTextChannel().clearReactionsById(event.getMessageIdLong()).queue();
            }
        }
    }

    private void cleanup(MessageReceivedEvent event) {
        RestAction<List<Message>> messageList = event.getChannel().getHistoryAfter(event.getMessageIdLong(), 5).complete().retrievePast(5);

        for (Message message : messageList.complete()) {
            if (message.getReactions().size() > 0 || message.isPinned()) {
                continue;
            }

            if (message.getAuthor().isBot() || message.getContentRaw().contains(this.settings.getCommandPrefix())) {
                message.delete().queue();
            }
        }
    }
}
