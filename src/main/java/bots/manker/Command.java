package bots.manker;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Command class for the bot.
 * here all the commands are handled.
 */
public class Command {

    /**
     * method that sends a meme to the channel of the event
     * @param event the event of a message received.
     */
    public static void sendMeme(MessageReceivedEvent event) {
        List<File> memes = getAllFiles(event);
        Random ramdom = new Random();
//            System.out.println("memes: " + memes.size());
        int ramdomInteger = ramdom.nextInt(memes.size());
        File meme = memes.get(ramdomInteger);

        event.getChannel().sendFile(meme).queue();
    }

    /**
     * method that retrieves all memes from a folder.
     * @param event the event of a message received.
     * @return a list of files containing all memes.
     */
    public static List<File> getAllFiles(MessageReceivedEvent event) {
        List<File> memes = new ArrayList<>();

        System.out.println("--- getting memes ---");

        // get all images ending with .jpg
        try (Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\Sem\\OneDrive\\Afbeeldingen\\memes"))) {

            List<String> resultjpg = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".jpg")).collect(Collectors.toList());

            for (String url : resultjpg) {
                File file = new File(url);
//                System.out.println("url: " + url);
                memes.add(file);
            }

            //get all png's
            Stream<Path> walkpng = Files.walk(Paths.get("C:\\Users\\Sem\\OneDrive\\Afbeeldingen\\memes"));

            List<String> resultpng = walkpng.map(Path::toString)
                    .filter(f -> f.endsWith(".png")).collect(Collectors.toList());
            for (String url : resultpng) {
                File file = new File(url);
//                System.out.println("url: " + url);
                memes.add(file);
            }
            // get all gifs
            Stream<Path> walkgif = Files.walk(Paths.get("C:\\Users\\Sem\\OneDrive\\Afbeeldingen\\memes"));

            List<String> resultgif = walkgif.map(Path::toString)
                    .filter(f -> f.endsWith(".gif")).collect(Collectors.toList());
            for (String url : resultgif) {
                File file = new File(url);
//                System.out.println("url: " + url);
                memes.add(file);
            }


        } catch (IOException e) {
            event.getChannel().sendMessage("there was an error: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalStateException e) {
            event.getChannel().sendMessage("there was an error: " + e.getMessage());
            e.printStackTrace();
        }
        return memes;
    }

    public static void sendHelpInfo(MessageReceivedEvent event) {
        String helpmsg = printHelp();
        event.getChannel().sendMessage(helpmsg).queue();
    }

    public static String printHelp() {
        String res = "-========================= General help =========================-\n" +
                "**(This bot is still very early stage, so things are subject to change)**\n" +
                "Keyword: !manker\n\n" +
                "!manker meme : posts a random meme\n" +
                "!manker yeet : responds to your message with yeet\n" +
                "!manker help : displays this message\n" +
                "!manker search <search term> : searches a random image on \nGoogle related" +
                "to the search term" +  
                "\n\n\n made by: Sem van der Hoeven\n" +
                "-===========================================================-";
        return res;
    }

}
