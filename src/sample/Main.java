package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controller.ConfigHandler;
import sample.controller.Controller;
import sample.view.Habitat;


public class Main extends Application {
    private static ConfigHandler configHandler = new ConfigHandler();

    public static void main(String[] args) {
        configHandler.loadConfig();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Controller controller = Controller.getInstance();
            Habitat view = Habitat.getInstance();
            view.initScene();

            controller.initController(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}