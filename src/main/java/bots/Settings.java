package bots;

import io.github.cdimascio.dotenv.Dotenv;

public class Settings {
    private String token;
    private String commandPrefix;
    private String activity;
    private String botName;
    private String memeDirectory;

    public Settings(Dotenv dotenv) {
        this.token = dotenv.get("MANKER_BOT_TOKEN");
        this.commandPrefix = dotenv.get("MANKER_BOT_COMMAND_PREFIX");
        this.activity = dotenv.get("MANKER_BOT_ACTIVITY");
        this.botName = dotenv.get("MANKER_BOT_NAME");
        this.memeDirectory = dotenv.get("MEME_DIRECTORY");
    }

    public String getToken() {
        if (this.token == null) {
            return "token";
        }

        return token;
    }

    public String getCommandPrefix() {
        if (this.token == null) {
            return "!bot";
        }

        return commandPrefix;
    }

    public String getActivity() {
        if (this.token == null) {
            return "Botting";
        }

        return activity;
    }

    public String getBotName() {
        if (this.token == null) {
            return "CyberBot";
        }

        return botName;
    }

    public String getMemeDirectory() {
        return memeDirectory;
    }
}
