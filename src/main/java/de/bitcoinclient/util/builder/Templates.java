package de.bitcoinclient.util.builder;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Templates {
    String name;
    boolean isStatic;
    String version;
    String provider;
    String types;
    int minServiceCount = 1;
    ArrayList<Templates> otherTemplates;
    int memory = 2048;

    public Templates() {
        otherTemplates = new ArrayList<>();
    }

    public Templates(String name, boolean isStatic, String provider) {
        this.name = name;
        this.isStatic = isStatic;
        this.provider = provider;
        otherTemplates = new ArrayList<>();
    }

    public Templates(String name, boolean isStatic, String version, String provider) {
        this.name = name;
        this.isStatic = isStatic;
        this.version = version;
        this.provider = provider;
        otherTemplates = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public int getMinServiceCount() {
        return minServiceCount;
    }

    public void setMinServiceCount(int minServiceCount) {
        this.minServiceCount = minServiceCount;
    }

    public void addTemplate(Templates template) {
        if(!otherTemplates.contains(template)) {
            otherTemplates.add(template);
        }
    }

    public void removeTemplate(Templates template) {
        if(otherTemplates.contains(template)) {
            otherTemplates.remove(template);
        }
    }

    public ArrayList<Templates> getOtherTemplates() {
        return otherTemplates;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getMemory() {
        return memory;
    }

    public void setType(String types) {
        this.types = types;
    }

    public String getType() {
        return types;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("static",isStatic);
        if(provider.equalsIgnoreCase("WATERFALL") || provider.equalsIgnoreCase("BUNGEECORD")) {
            jsonObject.addProperty("version", "bungee");
        } else {
            jsonObject.addProperty("version", version);
        }
        jsonObject.addProperty("provider",provider);
        jsonObject.addProperty("minServiceCount",minServiceCount);
        jsonObject.addProperty("memory",memory);
        jsonObject.addProperty("type", types);
        return jsonObject;
    }
}
