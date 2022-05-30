package sample.controller;

import sample.model.Model;
import sample.view.Habitat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PropertyPackage implements Serializable {

    public HashMap<String, Integer> getSimulationProperties() {
        return simulationProperties;
    }

    private final HashMap<String, Integer> simulationProperties = new HashMap<>();
    transient Model model = Model.getInstance();
    transient Habitat view = Habitat.getInstance();





    public void putProperties() {
        simulationProperties.put("alThreadPriority", model.getAlThreadPriority());
        simulationProperties.put("crThreadPriority", model.getCrThreadPriority());
        simulationProperties.put("crChance", model.getCrChance());
        simulationProperties.put("alChance", model.getAlChance());
        simulationProperties.put("alLifeTime", model.getAlLifeTime());
        simulationProperties.put("crLifeTime", model.getCrLifeTime());
        simulationProperties.put("crTime", model.getCrTime());
        simulationProperties.put("alTime", model.getAlTime());
        simulationProperties.put("mainThreadPriority", model.getMainThreadPriority());
    }

    public void setProperties() {
        if (model == null) {
            model = Model.getInstance();
        }
        if (view == null) {
            view = Habitat.getInstance();
        }
        model.setAlThreadPriority(simulationProperties.get("alThreadPriority"));
        model.setCrThreadPriority(simulationProperties.get("crThreadPriority"));
        model.setCrChance(simulationProperties.get("crChance"));
        model.setAlChance(simulationProperties.get("alChance"));
        model.setAlLifeTime(simulationProperties.get("alLifeTime"));
        model.setCrLifeTime(simulationProperties.get("crLifeTime"));
        model.setCrTime(simulationProperties.get("crTime"));
        model.setAlTime(simulationProperties.get("alTime"));
        model.setMainThreadPriority(simulationProperties.get("mainThreadPriority"));


        view.getAlThreadPriorityComBox().setValue(model.getAlThreadPriority());
        view.getCrThreadPriorityComBox().setValue(model.getCrThreadPriority());
        view.getCrChanceBox().setValue( model.getCrChance());
        view.getAlChanceBox().setValue(model.getAlChance());
        view.getTextFieldAlLifeTime().setText(String.valueOf( model.getAlLifeTime()));
        view.getTextFieldCrLifeTime().setText(String.valueOf( model.getCrLifeTime()));
        view.getTextFieldCrDelay().setText(String.valueOf(model.getCrTime()));
        view.getTextFieldAlDelay().setText(String.valueOf(model.getAlTime()));
        view.getMainThreadPriorityComBox().setValue(model.getMainThreadPriority());

        Controller controller = Controller.getInstance();
        controller.updateStats();
    }

    public void printProperties() {
        for (Map.Entry<String, Integer> pair : simulationProperties.entrySet()) {
            Integer value = pair.getValue();
            System.out.println(value);
        }
    }

}
