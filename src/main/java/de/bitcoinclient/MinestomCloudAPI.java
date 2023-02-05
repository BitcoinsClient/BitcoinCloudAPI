package de.bitcoinclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.bitcoinclient.util.HTTP;

public class MinestomCloudAPI {

    public static String setOnline(int port) {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/minestom?setOnline&"+port));
        return json.get("type").getAsString();
    }

    public static int getPort() {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/minestom?getPort"));
        if(json.get("type").getAsInt() != 201) {
            return 0;
        }
        return json.get("value").getAsInt();
    }

    public static String getIP() {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/minestom?getIP"));
        return json.get("value").getAsString();
    }

    private static JsonObject parse(String s) {
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(s);
    }
}
