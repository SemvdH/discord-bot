package bots.manker.commands;

import bots.Command;
import bots.Settings;

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

public class MemeCommand extends Command {
    public static final String COMMAND_NAME = "meme";

    public MemeCommand(Settings settings) {
        super(settings);
    }

    @Override
    public void execute() {
        List<File> memes = getAllFiles();
        Random ramdom = new Random();
//            System.out.println("memes: " + memes.size());
        int ramdomInteger = ramdom.nextInt(memes.size());
        File meme = memes.get(ramdomInteger);

        this.event.getChannel().sendFile(meme).queue();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * method that retrieves all memes from a folder.
     * @return a list of files containing all memes.
     */
    private List<File> getAllFiles() {
        List<File> memes = new ArrayList<>();

        System.out.println("--- getting memes ---");

        // get all images ending with .jpg
        try (Stream<Path> walk = Files.walk(Paths.get(this.settings.getMemeDirectory()))) {

            List<String> resultjpg = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".jpg")).collect(Collectors.toList());

            for (String url : resultjpg) {
                File file = new File(url);
//                System.out.println("url: " + url);
                memes.add(file);
            }

            //get all png's
            Stream<Path> walkpng = Files.walk(Paths.get(this.settings.getMemeDirectory()));

            List<String> resultpng = walkpng.map(Path::toString)
                    .filter(f -> f.endsWith(".png")).collect(Collectors.toList());
            for (String url : resultpng) {
                File file = new File(url);
//                System.out.println("url: " + url);
                memes.add(file);
            }
            // get all gifs
            Stream<Path> walkgif = Files.walk(Paths.get(this.settings.getMemeDirectory()));

            List<String> resultgif = walkgif.map(Path::toString)
                    .filter(f -> f.endsWith(".gif")).collect(Collectors.toList());
            for (String url : resultgif) {
                File file = new File(url);
//                System.out.println("url: " + url);
                memes.add(file);
            }


        } catch (IOException e) {
            this.event.getChannel().sendMessage("there was an error: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalStateException e) {
            this.event.getChannel().sendMessage("there was an error: " + e.getMessage());
            e.printStackTrace();
        }
        return memes;
    }
}
