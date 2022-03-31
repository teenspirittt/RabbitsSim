package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;

import java.security.Key;
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
        radButtonShowStatsLogic();
        radButtonHideStatsLogic();
        comBoxCommonLogic();
        comBoxAlbinoLogic();
        textAreaAlDelayLogic();
        textAreaCrDelayLogic();
        fileMenuLogic();
        runMenuLogic();
    }

    public void update(int time) {
        int changesCounter = 0;
        int alSummaryChance = (int) (model.getAlCount() / 0.2);

        if (time % model.getCrTime() == 0 && model.getCrChance() >= random.nextInt(100)) {
            model.setCrCount(model.getCrCount() + 1);
            changesCounter++;
            model.getRabbitsList().add(new CommonRabbit());
        }

        if (time % model.getAlTime() == 0 && alSummaryChance < model.getRabbitsList().size() && model.getAlChance() >= random.nextInt(100)) {
            model.setAlCount(model.getAlCount() + 1);
            changesCounter++;
            model.getRabbitsList().add(new AlbinoRabbit());
        }

        for (int i = model.getRabbitsList().size() - changesCounter; i < model.getRabbitsList().size(); ++i) {
            model.getRabbitsList().get(i).spawn(random.nextInt(view.getSceneWidth() - 318), random.nextInt(view.getSceneHeight() - 98), view.getRoot());
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


    private void keyShowStatsLogic() {
        model.setStatsVisible(!model.isStatsVisible());
        view.getRabbitCount().setVisible(model.isStatsVisible());
        if (model.isStatsVisible() == true)
            view.getShowStats().fire();
        else
            view.getHideStats().fire();

    }

    public void keyBinds() {
        view.getScene().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case B -> startLogic();
                case E -> pauseLogic();
                case T -> keyShowStatsLogic();
                case S -> stopLogic();
            }
        });
    }

    private void startLogic() {
        if (!model.isTimerWorking()) {
            timer = startTimer();
            view.getStartButton().setDisable(true);
            view.getStopButton().setDisable(false);
            view.getPauseButton().setDisable(false);
            view.getStartMenuItem().setDisable(true);
            view.getStopMenuItem().setDisable(false);
            view.getPauseMenuItem().setDisable(false);
            model.setTimerWorking(true);
            view.getPauseButton().setVisible(true);
            view.getStartButton().setVisible(false);

        }
    }

    private void pauseLogic() {
        timer.cancel();
        view.getStartButton().setDisable(false);
        view.getStopButton().setDisable(true);
        view.getPauseButton().setDisable(true);
        view.getStartMenuItem().setDisable(false);
        view.getStopMenuItem().setDisable(true);
        view.getPauseMenuItem().setDisable(true);
        model.setTimerWorking(false);
        view.getPauseButton().setVisible(false);
        view.getStartButton().setVisible(true);
    }

    private void stopLogic() {
        for (Rabbit rabbit : model.getRabbitsList()) {
            rabbit.delete(view.getRoot());
        }
        model.setTimerWorking(false);
        model.getRabbitsList().clear();
        model.resetStats();
        updateStats();
        view.getStopButton().setDisable(true);
        view.getStartButton().setDisable(false);
        view.getPauseButton().setDisable(true);
        view.getStopMenuItem().setDisable(true);
        view.getStartMenuItem().setDisable(false);
        view.getPauseMenuItem().setDisable(true);
        view.getStartButton().setVisible(true);
        view.getPauseButton().setVisible(false);
        timer.cancel();
    }

    private void buttonStartLogic() {
        view.getStartButton().setOnAction(ActionEvent -> {
            startLogic();
        });
    }

    private void buttonPauseLogic() {
        view.getPauseButton().setOnAction(ActionEvent -> {
            pauseLogic();
        });
    }

    private void buttonStopLogic() {
        view.getStopButton().setOnAction(ActionEvent -> {
            stopLogic();
        });
    }


    private void updateStats() {
        view.getRabbitCount().setFill(Color.web("669933"));
        view.getRabbitCount().setText("Time: " + model.gettTick() +
                "\nClassic Rabbits: " + model.getCrCount() +
                "\nAlbino Rabbits: " + model.getAlCount() +
                "\nClassic chance: " + model.getCrChance() +
                "\nClassic delay: " + model.getCrTime() +
                "\nAlbino chance: " + model.getAlChance() +
                "\nAlbino delay:" + model.getAlTime());
    }

    private void radButtonHideStatsLogic() {
        view.getHideStats().setOnAction(ActionEvent -> {
            model.setStatsVisible(false);
            view.getRabbitCount().setVisible(model.isStatsVisible());
        });
    }

    private void radButtonShowStatsLogic() {
        view.getShowStats().setOnAction(ActionEvent -> {
            model.setStatsVisible(true);
            view.getRabbitCount().setVisible(model.isStatsVisible());
        });
    }

    private void comBoxAlbinoLogic() {
        view.getAlChanceBox().setOnAction(ActionEvent -> {
            model.setAlChance(view.getAlChanceBox().getValue());
        });
    }

    private void comBoxCommonLogic() {
        view.getCrChanceBox().setOnAction(ActionEvent -> {
            model.setCrChance(view.getCrChanceBox().getValue());
        });
    }

    private void textAreaAlDelayLogic() {
        view.getTextFieldAlDelay().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    view.getTextFieldAlDelay().setText(newValue.replaceAll("\\D", ""));
                    view.getIncorrectInput().show();
                }
            }
        });
        view.getTextFieldAlDelay().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                model.setAlTime(Integer.parseInt(view.getTextFieldAlDelay().getText()));
            }
        });
    }

    private void textAreaCrDelayLogic() {
        view.getTextFieldCrDelay().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    view.getTextFieldCrDelay().setText(newValue.replaceAll("\\D", ""));
                    view.getIncorrectInput().show();
                }
            }
        });
        view.getTextFieldCrDelay().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                model.setCrTime(Integer.parseInt(view.getTextFieldCrDelay().getText()));
            }
        });

    }

    private void fileMenuLogic() {
        // view.getExitMenuItem().setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
        view.getExitMenuItem().setOnAction(ActionEvent -> {
            System.exit(0);
        });
    }

    private void runMenuLogic() {
        view.getStartMenuItem().setAccelerator(KeyCombination.keyCombination("B"));
        view.getStartMenuItem().setOnAction(ActionEvent -> {
            startLogic();
        });
        view.getPauseMenuItem().setAccelerator(KeyCombination.keyCombination("E"));
        view.getPauseMenuItem().setOnAction(ActionEvent -> {
            pauseLogic();
        });

        view.getStopMenuItem().setOnAction(ActionEvent -> {
            stopLogic();
        });
    }

}
