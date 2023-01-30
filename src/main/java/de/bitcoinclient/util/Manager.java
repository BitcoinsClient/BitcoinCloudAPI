package de.bitcoinclient.util;

import com.google.gson.JsonObject;
import de.bitcoinclient.util.builder.Module;

public class Manager {

    public static Module convertFromJson(JsonObject object) {
        Module module = new Module();
        module.setName(object.get("name").getAsString());
        module.setAuthors(object.get("authors").getAsString());
        module.setFileName(object.get("fileName").getAsString());
        module.setVersion(object.get("version").getAsString());
        module.setType(object.get("type").getAsString());
        module.setRequired(object.get("required").getAsBoolean());
        module.setDescription(object.get("description").getAsString());
        return module;
    }
}
