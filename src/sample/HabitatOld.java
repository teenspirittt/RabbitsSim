package sample;


import javafx.application.Application;
import javafx.stage.Stage;


public class Habitat extends Application {

    public static void main(String[] args) throws Exception {
        Habitat.launch(args);

    }


    public void start(Stage primaryStage) throws Exception {
        try {
            View view = View.getInstance();
            view.initScene();
            Controller controller = Controller.getInstance();
            controller.initController(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void initScene() {
        Stage stage = new Stage();
        Image icon = new Image("resources/rabbitIcon.jpg");

        Rectangle r = new Rectangle(1050, 0, 1280, 720);
        r.setOpacity(0.1);


        root.getChildren().addAll(r, rabbitCount);

        buttonPauseInit();
        buttonStartInit();
        buttonStopInit();



        stage.getIcons().add(icon);
        stage.setTitle("Rabbits");
        stage.setScene(scene);
        stage.show();
    }*/

  /*  private Timer startTimer() {
        timer = new Timer();
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
    }*/


    /*public void update(int time) {
        int changesCounter = 0;
      //  int crChance = 70;
        int alSummaryChance = (int) (alCount / 0.2);
       // int crTime = 2;
      //  int alChance = 20;
       // int alTime = 2;

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
    }*/

  /*  private void resetStats() {
        crCount = 0;
        alCount = 0;
        tTick = 0;
        rabbits.clear();
    }*/

   /* public void showStats() {
        rabbitCount.setVisible(isStatsVisible);
        rabbitCount.setText("Big Chungus World:\n["
                + tTick + "] seconds\n" +
                "Classic rabbits born: " + crCount +
                "\nAlbino rabbits born: " + alCount);
        rabbitCount.setFont(Font.font("Montserrat", 22));
        rabbitCount.setX(1060);
        rabbitCount.setY(50);
    }*/


   /* private void buttonStartInit() {
        startButton.setLayoutX(1118);
        startButton.setLayoutY(150);
        buttonStartLogic();
        startButton.setDisable(false);
        root.getChildren().add(startButton);
    }*/

   /* private void buttonStartLogic() {
        startButton.setOnAction(ActionEvent -> {
            if (isTimerWorking == false) {

                startButton.setVisible(false);
                pauseButton.setVisible(true);

                startButton.setDisable(true);
                stopButton.setDisable(false);
                isTimerWorking = true;
                startTimer();
            }
        });
    }*/

   /* private void buttonStopInit() {
        stopButton.setLayoutX(1174);
        stopButton.setLayoutY(150);
        buttonStopLogic();
        stopButton.setDisable(true);
        root.getChildren().add(stopButton);
    }*/

   /* private void buttonStopLogic() {
        stopButton.setOnAction(ActionEvent -> {
            for (Rabbit rabbit : rabbits) {
                rabbit.delete(root);
            }
            isTimerWorking = false;
            rabbits.clear();
            resetStats();
            showStats();
            stopButton.setDisable(true);

            pauseButton.setVisible(false);
            startButton.setVisible(true);

            startButton.setDisable(false);
            timer.cancel();
        });
    }*/

    /*private void buttonPauseInit() {
        pauseButton.setLayoutX(1118);
        pauseButton.setLayoutY(150);
        buttonPauseLogic();
        pauseButton.setDisable(false);
        pauseButton.setVisible(false);
        root.getChildren().add(pauseButton);
    }*/

   /* private void buttonPauseLogic() {
        pauseButton.setOnAction(ActionEvent -> {
            timer.cancel();
            startButton.setDisable(false);
            stopButton.setDisable(true);
            isTimerWorking = false;
            pauseButton.setVisible(false);
            startButton.setVisible(true);
        });
    }*/

}
