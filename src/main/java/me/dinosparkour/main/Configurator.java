package me.dinosparkour.main;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Configurator {

    private static final File config = new File("config.json");

    static JSONObject getConfig() {
        if (!config.exists()) {
            try {
                Configurator.create();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("Created a configuration file. Please fill the login credentials!");
            System.exit(0);
        }

        JSONObject object = null;
        try {
            object = Configurator.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return object;
    }

    private static void create() throws IOException {
        Files.write(Paths.get(config.getPath()),
                new JSONObject()
                        .put("email", "")
                        .put("password", "")
                        .put("prefix", "!")
                        .toString(4).getBytes());
    }

    private static JSONObject load() throws IOException {
        JSONObject object = new JSONObject(new String(Files.readAllBytes(Paths.get(config.getPath())), "UTF-8"));

        if (object.has("email") && object.has("password") && object.has("prefix"))
            return object;

        Configurator.create();
        System.err.println("The config file was missing a value! [Either email/password/prefix]");
        System.out.println("Regenerating the file from scratch..");
        System.exit(1);

        return null;
    }

    static void write(ConfigKey key, String value) throws IOException {
        JSONObject json = new JSONObject(new String(Files.readAllBytes(Paths.get(config.getPath())), "UTF-8"));

        switch (key) {
            case EMAIL:
                json.remove("email");
                json.put("email", value);
                break;

            case PASSWORD:
                json.remove("password");
                json.put("password", value);
                break;

            case PREFIX:
                json.remove("prefix");
                json.put("prefix", value);
                break;
        }

        Files.write(Paths.get(config.getPath()), json.toString(4).getBytes());
    }

    enum ConfigKey {EMAIL, PASSWORD, PREFIX}
}