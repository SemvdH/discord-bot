package bots.manker.commands;

import bots.manker.GoogleSearch;

import java.io.IOException;

public class SearchCommand extends Command {
    public static final String COMMAND_NAME = "search";

    @Override
    public void execute() {
        String searchQuery = this.parseParameter();

        System.out.println("the received query was: " + searchQuery);
        try {
            event.getChannel().sendMessage(GoogleSearch.search(searchQuery)).queue();
        } catch (IOException e) {
            e.printStackTrace();
            event.getChannel().sendMessage("There was an error while looking for an image!").queue();
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
