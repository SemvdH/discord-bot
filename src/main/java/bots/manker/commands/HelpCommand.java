package bots.manker.commands;

import bots.Command;
import bots.Settings;

public class HelpCommand extends Command {
    public static final String COMMAND_NAME = "help";

    public HelpCommand(Settings settings) {
        super(settings);
    }

    @Override
    public void execute() {
        String helpmsg = printHelp();
        event.getChannel().sendMessage(helpmsg).queue();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    private String printHelp() {
        String res = "-========================= General help =========================-\n" +
                "**(This bot is still very early stage, so things are subject to change)**\n" +
                "Keyword: !manker\n\n" +
                "!manker meme : posts a random meme\n" +
                "!manker yeet : responds to your message with yeet\n" +
                "!manker help : displays this message\n" +
                "!manker search <search term> : searches a random image on \nGoogle related " +
                "to the search term\n" +
                "!manker choose <options seperated by comma>\n" +
                "\n\n\n made by: Sem van der Hoeven & Ben van der Heiden\n" +
                "-===========================================================-";
        return res;
    }
}
