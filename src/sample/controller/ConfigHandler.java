package sample.controller;

import sample.model.Model;

import java.io.*;
import java.util.Properties;

public class ConfigHandler {
    Model model = Model.getInstance();

    public void saveConfig() {
        try (FileOutputStream fos = new FileOutputStream("src/resources/data/config.properties");) {
            Properties properties = new Properties();
            properties.setProperty("albino_spawn_chance", Integer.toString(model.getAlChance()));
            properties.setProperty("common_spawn_chance", Integer.toString(model.getCrChance()));
            properties.setProperty("albino_spawn_delay", Integer.toString(model.getAlTime()));
            properties.setProperty("common_spawn_delay", Integer.toString(model.getCrTime()));
            properties.setProperty("albino_lifetime", Integer.toString(model.getAlLifeTime()));
            properties.setProperty("common_lifetime", Integer.toString(model.getCrLifeTime()));
            properties.setProperty("albino_thread_priority", Integer.toString(model.getAlThreadPriority()));
            properties.setProperty("common_thread_priority", Integer.toString(model.getCrThreadPriority()));
            properties.setProperty("main_thread_priority", Integer.toString(model.getMainThreadPriority()));
            properties.store(fos, "cfg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {
        try (FileInputStream fis = new FileInputStream("src/resources/data/config.properties");) {
            Properties properties = new Properties();
            properties.load(fis);
            configReader(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void configReader(Properties properties) {
        int num;
        num = Integer.parseInt(properties.getProperty("albino_spawn_chance", "100"));
        if (num >= 0 && num <= 100 && num % 10 == 0) {
            model.setAlChance(num);
        }
        num = Integer.parseInt(properties.getProperty("common_spawn_chance", "100"));
        if (num >= 0 && num <= 100 && num % 10 == 0) {
            model.setCrChance(num);
        }
        num = Integer.parseInt(properties.getProperty("albino_spawn_delay", "100"));
        if (num >= 0 && num <= 60 && num % 10 == 0) {
            model.setAlTime(num);
        }
        num = Integer.parseInt(properties.getProperty("common_spawn_delay", "100"));
        if (num >= 0 && num <= 60 && num % 10 == 0) {
            model.setCrTime(num);
        }
        num = Integer.parseInt(properties.getProperty("albino_lifetime", "100"));
        if (num >= 0 && num <= 999 && num % 10 == 0) {
            model.setAlLifeTime(num);
        }
        num = Integer.parseInt(properties.getProperty("common_lifetime", "100"));
        if (num >= 0 && num <= 999 && num % 10 == 0) {
            model.setCrLifeTime(num);
        }
        num = Integer.parseInt(properties.getProperty("albino_thread_priority", "100"));
        if (num >= 0 && num <= 10 && num % 10 == 0) {
            model.setAlThreadPriority(num);
        }
        num = Integer.parseInt(properties.getProperty("common_thread_priority", "100"));
        if (num >= 0 && num <= 10 && num % 10 == 0) {
            model.setCrThreadPriority(num);
        }
        num = Integer.parseInt(properties.getProperty("main_thread_priority", "100"));
        if (num >= 0 && num <= 10 && num % 10 == 0) {
            model.setMainThreadPriority(num);
        }
    }


}
