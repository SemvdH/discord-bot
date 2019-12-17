package bots.manker.functionalities.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * JSONFunctions
 */
public class JSONFunctions {

    public static boolean containsID(String id, JSONArray jsonArray) {
        boolean contains = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject current = (JSONObject)jsonArray.get(i);
            String tempID = (String)current.get("id");
            if (tempID == id) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}