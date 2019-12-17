package bots.manker.commands;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import bots.Command;
import bots.Settings;
import bots.manker.functionalities.playerstats.Player;

/**
 * StatsCommand
 */
public class StatsCommand extends Command {
    public static final String COMMAND_NAME = "stats";
    private Player player;

    public StatsCommand(Settings settings) {
        super(settings);
    }

    @Override
    public void execute() {
        this.player = new Player(event.getAuthor().getId(), event.getAuthor().getName());
        try {
            this.player.writeAsJSON();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    
}