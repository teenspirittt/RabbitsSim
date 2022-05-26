package sample.controller;

import sample.model.Model;

import java.io.Serializable;
import java.util.HashMap;

public class PropertyPackage implements Serializable {

    private HashMap<String,Integer> simulationProperties = new HashMap<>();

    Model model = Model.getInstance();

    public void putProperties() {
        simulationProperties.put("alThreadPriority",model.getAlThreadPriority());
        simulationProperties.put("crThreadPriority",model.getCrThreadPriority());
        simulationProperties.put("crChance",model.getCrChance());
        simulationProperties.put("alChance",model.getAlChance());
        simulationProperties.put("alLifeTime",model.getAlLifeTime());
        simulationProperties.put("crLifeTime",model.getCrLifeTime());
        simulationProperties.put("crTime",model.getCrTime());
        simulationProperties.put("alTime",model.getAlTime());
        simulationProperties.put("mainThreadPriority",model.getMainThreadPriority());
    }
}
