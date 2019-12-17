package bots.manker;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageAnalyzer {
    private static final String REPLACEMENT = "bobba";

    private String[] meanWords = {
        "boomer", "kut", "kanker", "fucking", "fuck", "Bethesda", "EA", "graftakken", "graf", "tering"
    };

    public String analyzeAndReplaceMeanWords(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.equals("kut")) {
            message = "Nee, het is 'vervelend'!";
        } else {
            while (true) {
                String replacement = this.findAndReplaceMeanWord(message);
    
                if (replacement == null) {
                    break;
                }
    
                message = replacement;
            }

        }


        return message;
    }

    public boolean hasMeanWords(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase();

        for (String meanWord : this.meanWords) {
            if (message.contains(meanWord.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    private String findAndReplaceMeanWord(String message) {
        String lowercaseMessage = message.toLowerCase();

        for (String meanWord : this.meanWords) {
            int meanWordIndex = lowercaseMessage.indexOf(meanWord.toLowerCase());

            if (meanWordIndex >= 0) {
                String begin = message.substring(0, meanWordIndex);
                String end = message.substring(meanWordIndex + meanWord.length());
                return begin + REPLACEMENT + end;
            }
        }

        return null;
    }
}
