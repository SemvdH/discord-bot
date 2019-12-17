package bots.manker;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class MessageAnalyzer {
    private static final String REPLACEMENT = "bobba";

    private String[] meanWords = {
        "boomer", "kut", "kanker", "fucking", "fuck", "Bethesda", "graftakken", "graf", "tering", "Jessica", "bug"
    };

    private Map<String, String> specialCases;

    public MessageAnalyzer() {
        this.specialCases = new HashMap<>();
        this.specialCases.put("kut", "Nee, het is vervelend!");
        this.specialCases.put("juice wrld", "rust in vrede sap wereld");
    }

    public String analyzeAndReplaceMeanWords(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
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

        for (Map.Entry<String, String> entry : this.specialCases.entrySet()) {
            if (message.toLowerCase().contains(entry.getKey().toLowerCase())) {
                event.getChannel().sendMessage(entry.getValue() + "\n").queue();
            }
        }
    }
}
