package de.bitcoinclient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.bitcoinclient.util.HTTP;
import de.bitcoinclient.util.Manager;
import de.bitcoinclient.util.builder.Module;
import de.bitcoinclient.util.builder.Service;
import de.bitcoinclient.util.builder.Templates;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.UUID;

public class BitcoinCloudAPI {
    /*public static void main(String[] args) {

    }*/

    private static JsonParser parser = new JsonParser();

    public static String stopCloud() {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/api?stopCloud"));
        int response = json.get("type").getAsInt();
        if(response != 403) {
            return json.get("value").getAsString();
        }
        return json.get("value").getAsString();
    }

    public static ArrayList<Module> getLoadedModules() {
        ArrayList<Module> modules = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(HTTP.getHttp("http://127.0.0.1:10000/api?loadedModules"));
        jsonArray.forEach(jsonElement -> {
            JsonObject object = jsonElement.getAsJsonObject();
            modules.add(Manager.convertFromJson(object));
        });
        return modules;
    }

    public static ArrayList<Module> getAllModules() {
        ArrayList<Module> modules = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(HTTP.getHttp("http://127.0.0.1:10000/api?allModules"));
        jsonArray.forEach(jsonElement -> {
            JsonObject object = jsonElement.getAsJsonObject();
            modules.add(Manager.convertFromJson(object));
        });
        return modules;
    }

    public static ArrayList<Module> getAvailableModules() {
        ArrayList<Module> modules = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(HTTP.getHttp("http://127.0.0.1:10000/api?availableModules"));
        jsonArray.forEach(jsonElement -> {
            JsonObject object = jsonElement.getAsJsonObject();
            modules.add(Manager.convertFromJson(object));
        });
        return modules;
    }

    public static int getPlayerSize(String serviceName) {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/api?getSizePlayers&"+serviceName));
        int response = json.get("type").getAsInt();
        if(response != 302) {
            return 0;
        }
        return Integer.parseInt(json.get("value").getAsString());
    }

    public static String startService(String serviceName) {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/api?startService&"+serviceName));
        String message = json.get("value").getAsString();
        return message;
    }

    public static String stopService(String serviceName) {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/api?stopService&"+serviceName));
        String message = json.get("value").getAsString();
        return message;
    }

    public static ArrayList<Service> getServices() {
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(HTTP.getHttp("http://127.0.0.1:10000/api?getServices"));
        ArrayList<Service> services = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            JsonObject object = element.getAsJsonObject();
            Service service = new Service();
            service.setName(object.get("name").getAsString());
            service.setPort(object.get("port").getAsInt());
            service.setUuid(UUID.fromString(object.get("uuid").getAsString()));
            service.setIp(object.get("ip").getAsString());
            service.setOnline(object.get("online").getAsBoolean());
            services.add(service);
        }
        return services;
    }

    public static Service getServiceInfos(String serviceName) {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/api?serviceInfo&name&"+serviceName));

        int response = json.get("type").getAsInt();
        if(response != 302) {
            return null;
        }

        Service service = new Service();
        service.setName(json.get("name").getAsString());
        service.setPort(json.get("port").getAsInt());
        service.setUuid(UUID.fromString(json.get("uuid").getAsString()));
        service.setIp(json.get("ip").getAsString());
        service.setOnline(json.get("online").getAsBoolean());
        return service;
    }

    public static Service getServiceInfos(UUID serviceUUID) {
        JsonObject json = parse(HTTP.getHttp("http://127.0.0.1:10000/api?serviceInfo&uuid&"+serviceUUID.toString()));

        int response = json.get("type").getAsInt();
        if(response != 302) {
            return null;
        }

        Service service = new Service();
        service.setName(json.get("name").getAsString());
        service.setPort(json.get("port").getAsInt());
        service.setUuid(UUID.fromString(json.get("uuid").getAsString()));
        service.setIp(json.get("ip").getAsString());
        service.setOnline(json.get("online").getAsBoolean());
        return service;
    }

    public static ArrayList<Templates> getTemplates() {
        JsonArray jsonArray = (JsonArray) parser.parse(HTTP.getHttp("http://127.0.0.1:10000/api?getTemplates"));
        ArrayList<Templates> allTemplates = new ArrayList<>();
        jsonArray.forEach(jsonElement -> {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Templates template;
            if(jsonObject.get("version").getAsString().equalsIgnoreCase("bungee")) {
                template = new Templates(jsonObject.get("name").getAsString(),
                        jsonObject.get("static").getAsBoolean(),
                        jsonObject.get("provider").getAsString());
                template.setMinServiceCount(jsonObject.get("minServiceCount").getAsInt());
                template.setMemory(jsonObject.get("memory").getAsInt());
                template.setType(jsonObject.get("type").getAsString());
                template.setVersion("PROXY");
            } else {
                template = new Templates(jsonObject.get("name").getAsString(),
                        jsonObject.get("static").getAsBoolean(),
                        jsonObject.get("version").getAsString(),
                        jsonObject.get("provider").getAsString());
                template.setMinServiceCount(jsonObject.get("minServiceCount").getAsInt());
                template.setMemory(jsonObject.get("memory").getAsInt());
                template.setType(jsonObject.get("type").getAsString());
            }
            allTemplates.add(template);
        });

        return allTemplates;
    }

    public static String getPrefix() {
        String prefix = "";
        JsonObject prefixJson = parse(HTTP.getHttp("http://127.0.0.1:10000/api?prefix"));
        prefix = prefixJson.get("value").getAsString();
        return prefix;
    }

    private static JsonObject parse(String s) {
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(s);
    }
}