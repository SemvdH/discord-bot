package bots.manker;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageAnalyzer {
    private static final String REPLACEMENT = "bobba";

    private String[] meanWords = {
        "boomer", "kut", "kanker", "fucking", "fuck", "Bethesda", "graftakken", "graf", "tering", "Jessica", "bug"
    };

    public String analyzeAndReplaceMeanWords(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        checkSpecialCases(event);
        while (true) {
            String replacement = this.findAndReplaceMeanWord(message);

            if (replacement == null) {
                break;
            }

            message = replacement;
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

                if (meanWord.equals("bug")) {
                    return begin + "feature" + end;
                }

                return begin + REPLACEMENT + end;
            }
        }

        return null;
    }

    public void checkSpecialCases(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (message.contains("kut")) {
            event.getChannel().sendMessage("Nee, het is vervelend!\n").queue();
        } else if(message.toLowerCase().contains("juice wrld")) {
            event.getChannel().sendMessage("rust in vrede sap wereld\n").queue();
        }

    
    }
}
