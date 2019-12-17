package bots.manker;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageAnalyzer {
    private static final String REPLACEMENT = "bobba";

    private String[] meanWords = {
        "boomer"
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
        for (String meanWord : this.meanWords) {
            if (event.getMessage().getContentRaw().contains(meanWord)) {
                return true;
            }
        }

        return false;
    }

    private String findAndReplaceMeanWord(String message) {
        for (String meanWord : this.meanWords) {
            int meanWordIndex = message.indexOf(meanWord);
            if (meanWordIndex > 0) {
                String begin = message.substring(0, meanWordIndex);
                String end = message.substring(meanWordIndex + meanWord.length());

                return begin + REPLACEMENT + end;
            }
        }

        return null;
    }
}
