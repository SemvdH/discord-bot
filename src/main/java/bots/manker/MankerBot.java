package bots.manker;

import bots.Bot;
import bots.Command;
import bots.Settings;
import bots.manker.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MankerBot extends Bot {
    public MankerBot(Settings settings) {
        super(settings);

        this.commandList.add(new FourTwentyCommand());
        this.commandList.add(new HelpCommand());
        this.commandList.add(new MemeCommand());
        this.commandList.add(new SearchCommand());
        this.commandList.add(new YeetCommand());
        this.commandList.add(new ChooseCommand());

        for (Command command : commandList) {
            command.setCommandPrefix(settings.getCommandPrefix());
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
    }
}
