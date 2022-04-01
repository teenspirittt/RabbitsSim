package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Habitat view = Habitat.getInstance();
            view.initScene();
            Controller controller = Controller.getInstance();
            controller.initController(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}