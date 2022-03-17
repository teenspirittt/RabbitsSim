package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final int sceneWidth = 1280;
    private final int sceneHeight = 720;
    private int crCount = 0, alCount = 0;
    private int tTick = 0;

    private final Group root = new Group();
    Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.DARKGREEN);

    Button stop = new Button("Stop");
    Button start = new Button("Start");

    private final ArrayList<Rabbit> rabbits = new ArrayList();
    private final Text rabbitCount = new Text();
    private final Random random = new Random();
    private Timer timer = new Timer();

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
                    switch (keyEvent.getCode()) {
                        case B:
                            if (isTimerWorking == false) {
                                isTimerWorking = true;
                                timer = startTimer();
                            }
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
        r.setOpacity(0.1);


        root.getChildren().add(r);
        root.getChildren().add(rabbitCount);

        buttonStartInit();
        buttonStopInit();


        stage.getIcons().add(icon);
        stage.setTitle("Rabbits");
        stage.setScene(scene);
        stage.show();
    }

    private Timer startTimer() {
        timer = new Timer();
        System.out.println("tupoe govno");

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
        int alSummaryChance = (int) (alCount / 0.2);
        int crTime = 2;
        int alChance = 20;
        int alTime = 2;

        if (time % crTime == 0 && crChance >= random.nextInt(100)) {
            crCount++;
            changesCounter++;
            rabbits.add(new CommonRabbit());
        }

        if (time % alTime == 0 && alSummaryChance <= rabbits.size() && alChance >= random.nextInt(100)) {
            alCount++;
            changesCounter++;
            rabbits.add(new AlbinoRabbit());
        }

        for (int i = rabbits.size() - changesCounter; i < rabbits.size(); ++i) {
            rabbits.get(i).spawn(random.nextInt(sceneWidth - 250), random.nextInt(sceneHeight - 70), root);
            root.getChildren().add(rabbits.get(i));
        }
    }

    private void resetStats() {
        crCount = 0;
        alCount = 0;
        tTick = 0;
        rabbits.clear();

    }

    public void showStats() {
        rabbitCount.setVisible(isStatsVisible);
        rabbitCount.setText("Big Chungus World:\n["
                + tTick + "] seconds\n" +
                "Classic rabbits born: " + crCount +
                "\nAlbino rabbits born: " + alCount);
        rabbitCount.setFont(Font.font("Montserrat", 22));
        rabbitCount.setX(1060);
        rabbitCount.setY(50);
    }

    private void updateStats(){

    }

    private void buttonStartInit() {

        start.setLayoutX(1118);
        start.setLayoutY(150);
        buttonStartLogic(start);
        start.setDisable(false);
        root.getChildren().add(start);
    }

    private void buttonStartLogic(Button start) {
        start.setOnAction(ActionEvent -> {
            if (isTimerWorking == false) {
                start.setDisable(true);
                stop.setDisable(false);
                startTimer();
            }
        });
    }

    private void buttonStopInit() {
        stop.setLayoutX(1174);
        stop.setLayoutY(150);
        buttonStopLogic(stop);
        stop.setDisable(true);
        root.getChildren().add(stop);
    }

    private void buttonStopLogic(Button stop) {
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < alCount + crCount; ++i) {
                    for (Rabbit rabbit : rabbits) {
                        rabbit.delete(root);
                    }
                }
                rabbits.clear();
                resetStats();
                showStats();
                stop.setDisable(true);
                start.setDisable(false);
                timer.cancel();
            }
        });
    }
}
