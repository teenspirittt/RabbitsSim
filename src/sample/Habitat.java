package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Habitat extends Application {
    private final Group root = new Group();
    private final int sceneWidth = 1280;
    private final int sceneHeight = 720;
    Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.DARKGREEN);
    private int crCount = 0, alCount = 0;
    final Random random = new Random();
    private final ArrayList<Rabbit> rabbits = new ArrayList();
    private int tTick = 0;
    Timer timer = new Timer();
    Text rabbitCount = new Text();
    private boolean isTimerWorking = false;
    private boolean isStatsVisible = false;

    public static void main(String[] args) throws Exception {
        Habitat.launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        try {

            initScene();
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    int a = 2;
                    switch (keyEvent.getCode()) {
                        case B:
                            if (isTimerWorking == false)
                                timer = startTimer();
                            break;
                        case E:
                            timer.cancel();
                            isTimerWorking = false;
                            //rabbits.clear();
                            break;
                        case T:
                            if (isStatsVisible == false)
                                isStatsVisible = true;
                            else
                                isStatsVisible = false;
                            break;

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initScene() {
        Stage stage = new Stage();
        Image icon = new Image("resources/rabbitIcon.jpg");

        Rectangle r = new Rectangle(1050, 0, 1280, 720);
        r.setOpacity(0.2);

        root.getChildren().add(r);
        root.getChildren().add(rabbitCount);

        stage.getIcons().add(icon);
        stage.setTitle("Rabbits");
        stage.setScene(scene);
        stage.show();
    }

    private Timer startTimer() {
        /*if (timer != null)
            timer.cancel();*/
        timer = new Timer();
        isTimerWorking = true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        update(tTick);
                        showStats();
                        tTick++;
                    }
                });
            }
        }, 1000, 1000);
        return timer;
    }


    public void update(int time) {
        int changesCounter = 0;
        int crChance = 70;
        int alChance = (int) (alCount / 0.2);
        int crTime = 2;
        int alChance1 = 20;
        int alTime = 2;

        if (time % crTime == 0 && crChance >= random.nextInt(100)) {
            crCount++;
            changesCounter++;
            rabbits.add(new CommonRabbit());
        }

        if (time % alTime == 0 && alChance <= rabbits.size() && alChance1 >= random.nextInt(100)) {
            alCount++;
            changesCounter++;
            rabbits.add(new AlbinoRabbit());
        }

        for (int i = rabbits.size() - changesCounter; i < rabbits.size(); ++i) {
            rabbits.get(i).spawn(random.nextInt(sceneWidth - 250), random.nextInt(sceneHeight - 70), root);
            root.getChildren().add(rabbits.get(i));
        }
    }


    public void showStats() {
        rabbitCount.setVisible(isStatsVisible);
        rabbitCount.setText("Big Chungus World:\n"
                + tTick + " seconds\n" +
                "classic rabbits born: " + crCount +
                "\nalbino rabbits born: " + alCount);

        rabbitCount.setFont(Font.font("Montserat", 22));
       /* if (isStatsVisible == true)
            rabbitCount.setFill(Color.BLACK);
        else
            rabbitCount.setFill(Color.DARKGREEN);*/
        rabbitCount.setX(1060);
        rabbitCount.setY(50);

    }
}
