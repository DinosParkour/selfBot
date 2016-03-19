package me.dinosparkour.main;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
class Configurator {
    private static File config = new File("config.json");

    static JSONObject getConfig() {
        if(!config.exists()) {
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

        if(object.has("email") && object.has("password") && object.has("prefix"))
            return object;

        Configurator.create();
        System.err.println("The config file was missing a value! [Either email/password/prefix]");
        System.out.println("Regenerating the file from scratch..");
        System.exit(1);

        return null;
    }

    static void write(String value, String key) throws IOException {
        switch (value.toLowerCase()) {
            case "email":
                Files.write(Paths.get(config.getPath()),
                        new JSONObject()
                                .put("email", key)
                                .put("password", BotInfo.getPassword())
                                .put("prefix", BotInfo.getPrefix())
                                .toString(4).getBytes());
                break;

            case "password":
                Files.write(Paths.get(config.getPath()),
                        new JSONObject()
                                .put("email", BotInfo.getEmail())
                                .put("password", key)
                                .put("prefix", BotInfo.getPrefix())
                                .toString(4).getBytes());
                break;

            case "prefix":
                Files.write(Paths.get(config.getPath()),
                        new JSONObject()
                                .put("email", BotInfo.getEmail())
                                .put("password", BotInfo.getPassword())
                                .put("prefix", key)
                                .toString(4).getBytes());
                break;

            default:
                throw new IllegalArgumentException(value + " is not a valid value");
        }
    }
}