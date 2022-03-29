package sample;

import javafx.application.Platform;
import javafx.scene.text.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {
    private static Controller instance;

    Model model = Model.getInstance();

    Habitat view;

    private final Random random = new Random();
    private Timer timer = new Timer();

    static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    Controller() {
    }

    public void initController(Habitat view) {
        this.view = view;
        keyBinds();
        buttonPauseLogic();
        buttonStartLogic();
        buttonStopLogic();
    }

    public void update(int time) {
        int changesCounter = 0;

        int alSummaryChance = (int) (model.getAlCount() / 0.2);


        if (time % model.getCrTime() == 0 && model.getCrChance() >= random.nextInt(100)) {
            model.setCrCount(model.getCrCount() + 1);
            changesCounter++;
            model.getRabbitsList().add(new CommonRabbit());
        }

        if (time % model.getAlTime() == 0 && alSummaryChance <= model.getRabbitsList().size() && model.getAlChance() >= random.nextInt(100)) {
            model.setAlCount(model.getAlCount() + 1);
            changesCounter++;
            model.getRabbitsList().add(new AlbinoRabbit());
        }

        for (int i = model.getRabbitsList().size() - changesCounter; i < model.getRabbitsList().size(); ++i) {
            model.getRabbitsList().get(i).spawn(random.nextInt(view.getSceneWidth() - 250), random.nextInt(view.getSceneHeight() - 70), view.getRoot());
            view.getRoot().getChildren().add(model.getRabbitsList().get(i));
        }
    }

    private Timer startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        update(model.gettTick());
                        updateStats();
                        model.settTick(model.gettTick() + 1);
                    }
                });
            }
        }, 1000, 1000);
        return timer;
    }

    private void keyPauseLogic() {
        timer.cancel();
        view.getStartButton().setDisable(false);
        view.getStopButton().setDisable(true);
        model.setTimerWorking(false);
        view.getPauseButton().setVisible(false);
        view.getStartButton().setVisible(true);
    }

    private void keyStartLogic() {
        timer = startTimer();
        view.getStartButton().setDisable(true);
        view.getStopButton().setDisable(false);
        model.setTimerWorking(true);
        view.getPauseButton().setVisible(true);
        view.getStartButton().setVisible(false);
    }

    private void keyShowStatsLogic() {

        model.setStatsVisible(!model.isStatsVisible());
        view.getRabbitCount().setVisible(model.isStatsVisible());
    }

    public void keyBinds() {
        view.getScene().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case B:
                    if (!model.isTimerWorking())
                        keyStartLogic();
                    break;
                case E:
                    keyPauseLogic();
                    break;
                case T:
                    keyShowStatsLogic();
                    break;
                default:
                    break;
            }
        });
    }

    private void buttonStartLogic() {
        view.getStartButton().setOnAction(ActionEvent -> {
            if (!model.isTimerWorking()) {
                startTimer();
                view.getStartButton().setDisable(true);
                view.getStopButton().setDisable(false);
                model.setTimerWorking(true);
                view.getPauseButton().setVisible(true);
                view.getStartButton().setVisible(false);
            }
        });
    }

    private void buttonStopLogic() {
        view.getStopButton().setOnAction(ActionEvent -> {

            for (Rabbit rabbit : model.getRabbitsList()) {
                rabbit.delete(view.getRoot());
            }
            model.setTimerWorking(false);
            model.getRabbitsList().clear();
            model.resetStats();
            updateStats();

            view.getStopButton().setDisable(true);
            view.getStartButton().setDisable(false);

            view.getStartButton().setVisible(true);
            view.getPauseButton().setVisible(false);

            timer.cancel();
        });
    }

    private void buttonPauseLogic() {
        view.getPauseButton().setOnAction(ActionEvent -> {
            timer.cancel();
            view.getStopButton().setDisable(true);
            view.getStartButton().setDisable(false);
            model.setTimerWorking(false);
            view.pauseButton.setVisible(false);
            view.getStartButton().setVisible(true);
        });
    }

    private void updateStats(){
        view.getRabbitCount().setText("Big Chungus World:\n["
                + model.gettTick() + "] seconds\n" +
                "Classic rabbits born: " + model.getCrCount() +
                "\nAlbino rabbits born: " + model.getAlCount());
    }
}
