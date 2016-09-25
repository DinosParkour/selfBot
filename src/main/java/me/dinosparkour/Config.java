package me.dinosparkour;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Config {

    private final File configFile = new File("config.json");
    private JSONObject configObject;

    Config() {
        if (!configFile.exists()) {
            create(); // If the config.json file doesn't exist, generate it.
            System.out.println("Created a config file. Please fill in the credentials.");
            System.exit(0);
        }

        JSONObject object = null;
        try {
            object = new JSONObject(new String(Files.readAllBytes(Paths.get(configFile.getPath())), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (object != null && object.has("token"))
            configObject = object;
        else {
            create(); // If the token value is missing, regenerate the config file.
            System.err.println("The token value is missing in the config file! Regenerating..");
            System.exit(1);
        }
    }

    String getValue(String key) {
        return configObject == null ? null : configObject.getString(key);
    }

    private void create() {
        try {
            Files.write(Paths.get(configFile.getPath()), new JSONObject().put("token", "").toString(4).getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}