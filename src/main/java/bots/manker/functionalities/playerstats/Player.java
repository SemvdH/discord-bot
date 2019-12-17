package bots.manker.functionalities.playerstats;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Player
 */
public class Player {

    private String id;
    private String name;

    private JSONArray jsonArray;

    public static final String JSON_FILE = "players.json";

    public Player() {
    }

    public Player(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + "}";
    }

    public void writeAsJSON() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        jsonArray = (JSONArray)parser.parse(new FileReader(JSON_FILE));
        
        JSONObject playerObject = new JSONObject();
        playerObject.put("id", this.id);
        playerObject.put("name", this.name);
        jsonArray.add(playerObject);
        Files.write(Paths.get(JSON_FILE), jsonArray.toJSONString().getBytes());
    }       

    public Player getFromJSON() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(JSON_FILE), Player.class);
    }

}