package sample.controller;

import javafx.stage.FileChooser;
import sample.model.Model;
import sample.rabbit.CommonRabbit;
import sample.rabbit.Rabbit;
import sample.view.Habitat;

import java.io.*;
import java.util.Vector;


public class LoadSaveController {
    Model model = Model.getInstance();
    Habitat habitat = Habitat.getInstance();

    public void saveRabbits() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/resources/data"));
        fileChooser.setInitialFileName("rabbits.dat");
        File file = fileChooser.showSaveDialog(habitat.getStage());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            for (int i = 0; i < model.getRabbitsVector().size(); ++i)
                oos.writeObject(model.getRabbitsVector().get(i));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadRabbits() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/resources/data"));
        File file = fileChooser.showSaveDialog(habitat.getStage());

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Rabbit rabbit = (Rabbit) ois.readObject();
                    rabbit.spawn(rabbit.getPosX(), rabbit.getPosY(), habitat.getRoot());
                    model.getRabbitsVector().add(rabbit);
                    model.getRabbitsIdSet().add(rabbit.getID());
                    model.getRabbitsLifeTimeMap().put(rabbit.getID(), model.gettTick());
                    if (rabbit instanceof CommonRabbit) {
                        model.setAlCount(model.getAlCount() + 1);
                    } else {
                        model.setCrCount(model.getCrCount() + 1);
                    }
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}