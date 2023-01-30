package de.bitcoinclient.util.builder;

import com.google.gson.JsonObject;

public class Module {
    String name;
    String authors;
    String fileName;
    String version;
    String type;
    boolean isRequired;
    String description;

    public Module() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAuthors() {
        return authors;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("name",getName());
        object.addProperty("authors",getAuthors());
        object.addProperty("fileName",getFileName());
        object.addProperty("version",getVersion());
        object.addProperty("type",getType());
        object.addProperty("required",isRequired());
        object.addProperty("description",getDescription());
        return object;
    }
}
