package bots.manker;

import bots.Bot;
import bots.Command;
import bots.Settings;
import bots.manker.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MankerBot extends Bot {
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
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
    }
}
