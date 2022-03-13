package sample;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Habitat extends Application {
   // public Group root;
    // public Scene scene = new Scene(root, 600, 600, Color.DARKGREEN);
    // public Stage stage;
    private int x, y;
    private int crCount = 0, alCount = 0;
    private int crChance = 70, alChance = 20;
    public int crTime = 3, alTime = 3;
    final Random random = new Random();
    ArrayList<Rabbit> rabbits = new ArrayList();


    public static void main(String[] args) throws Exception {
        Habitat.launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
       // initScene();

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.DARKGREEN);
        Stage stage = new Stage();

        Image icon = new Image("resources/rabbitIcon.jpg");
        stage.getIcons().add(icon);
        stage.setTitle("Rabbits");
        stage.setResizable(true);

        new Timer().schedule(
                new TimerTask() {
                    int tTick = 0;

                    @Override
                    public void run() {
                        update(tTick, root);
                        tTick++;
                    }
                }, 0, 1000);
        stage.setScene(scene);
        stage.show();
    }

    private void initScene() {



         /*AlbinoRabbit albinoRabbit = new AlbinoRabbit();
         CommonRabbit commonRabbit = new CommonRabbit();
         commonRabbit.spawn(30, 10);
         albinoRabbit.spawn(300, 50);

         root.getChildren().add(commonRabbit);
        root.getChildren().add(albinoRabbit);*/

    }


    public void update(int time, Group root) {
        alChance = (int) (alCount / 0.2);
        if (time % crTime == 0 && crChance >= random.nextInt(100)) {
            crCount++;
            rabbits.add(new CommonRabbit());
        }
        if (time % alTime == 0 && alChance <= rabbits.size()) {
            alCount++;
            rabbits.add(new AlbinoRabbit());
        }
        if (rabbits.size() > time) {
            rabbits.get(time).spawn(random.nextInt(300), random.nextInt(300));
            root.getChildren().add(rabbits.get(time));
        }
    }
}
