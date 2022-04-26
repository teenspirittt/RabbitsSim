package sample;

import sample.rabbit.*;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;


import java.util.Optional;
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

        advancedModeLogic(model.isAdvancedMode());

        buttonPauseLogic();
        buttonStartLogic();
        buttonStopLogic();
        radButtonShowStatsLogic();
        radButtonHideStatsLogic();
        comBoxCommonLogic();
        comBoxAlbinoLogic();
        textAreaAlDelayLogic();
        textAreaCrDelayLogic();
        textAreaAlLifeTimeLogic();
        textAreaCrLifeTimeLogic();
        fileMenuLogic();
        runMenuLogic();
        viewMenuLogic();
        helpMenuLogic();
    }

    public void update(int time) {
        int changesCounter = 0;
        int alSummaryChance = (int) (model.getAlCount() / 0.2);
        int passID;
        if (time % model.getCrTime() == 0 && model.getCrChance() >= random.nextInt(100)) {
            model.setCrCount(model.getCrCount() + 1);
            changesCounter++;
            model.getRabbitsVector().add(new CommonRabbit());
            model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).setBirthTime(time);
            model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).setLifeTime(model.getCrLifeTime());

            passID = random.nextInt(1000);
            while (model.getRabbitsIdSet().contains(passID)) {
                passID = random.nextInt(1000);
            }
            model.getRabbitsIdSet().add(passID);
            model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).setID(passID);
            model.getRabbitsLifeTimeMap().put(passID, model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).getBirthTime());
        }

        if (time % model.getAlTime() == 0 && alSummaryChance < model.getRabbitsVector().size() && model.getAlChance() >= random.nextInt(100)) {
            model.setAlCount(model.getAlCount() + 1);
            changesCounter++;
            model.getRabbitsVector().add(new AlbinoRabbit());
            model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).setBirthTime(time);
            model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).setLifeTime(model.getAlLifeTime());

            passID = random.nextInt(1000);
            while (model.getRabbitsIdSet().contains(passID)) {
                passID = random.nextInt(1000);
            }
            model.getRabbitsIdSet().add(passID);
            model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).setID(passID);
            model.getRabbitsLifeTimeMap().put(passID, model.getRabbitsVector().get(model.getRabbitsVector().size() - 1).getBirthTime());
        }

        for (int i = model.getRabbitsVector().size() - changesCounter; i < model.getRabbitsVector().size(); ++i) {
            model.getRabbitsVector().get(i).spawn(random.nextInt(view.getSceneWidth() - 318), random.nextInt(view.getSceneHeight() - 250), view.getRoot());
            view.getRoot().getChildren().add(model.getRabbitsVector().get(i));
        }

    }

    private Timer startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    update(model.gettTick());
                    updateRabbitsPopulation();
                    updateStats();
                    model.settTick(model.gettTick() + 1);
                });
            }
        }, 1000, 1000);
        return timer;
    }

    public void keyBinds() {
        view.getScene().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case B -> startLogic();
                case E -> pauseLogic();
                case T -> showStatsLogic();
                case S -> stopWithInfoLogic();
                case I -> advancedModeLogic(model.isAdvancedMode());
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

    private void stopWithInfoLogic() {
        timer.cancel();
        if (!model.isStatsVisible()) {
            view.getStopSimulation().setContentText("Time: " + model.gettTick() +
                    "\nClassic Rabbits: " + model.getCrCount() +
                    "\nAlbino Rabbits: " + model.getAlCount() +
                    "\nClassic chance: " + model.getCrChance() +
                    "\nClassic delay: " + model.getCrTime() +
                    "\nAlbino chance: " + model.getAlChance() +
                    "\nAlbino delay:" + model.getAlTime());
            Optional<ButtonType> option = view.getStopSimulation().showAndWait();
            if (option.get() == ButtonType.OK) {
                stopLogic();
            } else {
                startTimer();
            }
        } else {
            stopLogic();
        }
    }

    private void stopLogic() {
        for (Rabbit rabbit : model.getRabbitsVector()) {
            rabbit.delete(view.getRoot());
        }
        model.setTimerWorking(false);
        model.getRabbitsVector().clear();
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
    }

    private void showStatsLogic() {
        model.setStatsVisible(!model.isStatsVisible());
        view.getRabbitCount().setVisible(model.isStatsVisible());
        if (model.isStatsVisible())
            view.getShowStats().fire();
        else
            view.getHideStats().fire();
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
            stopWithInfoLogic();
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
            updateStats();
        });
    }

    private void comBoxAlbinoLogic() {
        view.getAlChanceBox().setValue(model.getAlChance());
        view.getAlChanceBox().setOnAction(ActionEvent -> {
            model.setAlChance(view.getAlChanceBox().getValue());
            updateStats();
        });
    }

    private void comBoxCommonLogic() {
        view.getCrChanceBox().setValue(model.getCrChance());
        view.getCrChanceBox().setOnAction(ActionEvent -> {
            model.setCrChance(view.getCrChanceBox().getValue());
            updateStats();
        });
    }

    private void textAreaAlDelayLogic() {
        view.getTextFieldAlDelay().setText(String.valueOf(model.getAlTime()));
        view.getTextFieldAlDelay().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                view.getTextFieldAlDelay().setText(newValue.replaceAll("\\D", ""));
                view.getIncorrectInput().show();
            }
        });
        view.getTextFieldAlDelay().setOnKeyPressed(keyEvent -> {
            if (!view.getTextFieldAlDelay().getText().equals("") && keyEvent.getCode() == KeyCode.ENTER) {
                model.setAlTime(Integer.parseInt(view.getTextFieldAlDelay().getText()));
                updateStats();
            }
        });
    }

    private void textAreaCrDelayLogic() {
        view.getTextFieldCrDelay().setText(String.valueOf(model.getCrTime()));
        view.getTextFieldCrDelay().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                view.getTextFieldCrDelay().setText(newValue.replaceAll("\\D", ""));
                view.getIncorrectInput().show();
            }
        });
        view.getTextFieldCrDelay().setOnKeyPressed(keyEvent -> {
            if (!view.getTextFieldCrDelay().getText().equals("") && keyEvent.getCode() == KeyCode.ENTER) {

                model.setCrTime(Integer.parseInt(view.getTextFieldCrDelay().getText()));
                updateStats();
            }
        });
    }

    private void textAreaAlLifeTimeLogic() {
        view.getTextFieldAlLifeTime().setText(String.valueOf(model.getAlLifeTime()));
        view.getTextFieldAlLifeTime().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                view.getTextFieldAlLifeTime().setText(newValue.replaceAll("\\D", ""));
                view.getIncorrectInput().show();
            }
        });
        view.getTextFieldAlLifeTime().setOnKeyPressed(keyEvent -> {
            if (!view.getTextFieldAlLifeTime().getText().equals("") && keyEvent.getCode() == KeyCode.ENTER) {
                model.setAlLifeTime(Integer.parseInt(view.getTextFieldAlLifeTime().getText()));
                updateStats();
            }
        });
    }

    private void textAreaCrLifeTimeLogic() {
        view.getTextFieldCrLifeTime().setText(String.valueOf(model.getCrLifeTime()));
        view.getTextFieldCrLifeTime().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                view.getTextFieldCrLifeTime().setText(newValue.replaceAll("\\D", ""));
                view.getIncorrectInput().show();
            }
        });
        view.getTextFieldCrLifeTime().setOnKeyPressed(keyEvent -> {
            if (!view.getTextFieldCrLifeTime().getText().equals("") && keyEvent.getCode() == KeyCode.ENTER) {
                model.setCrLifeTime(Integer.parseInt(view.getTextFieldCrLifeTime().getText()));
                updateStats();
            }
        });
    }

    private void fileMenuLogic() {
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
        view.getStopMenuItem().setAccelerator(KeyCombination.keyCombination("S"));
        view.getStopMenuItem().setOnAction(ActionEvent -> {
            stopWithInfoLogic();
        });
    }

    private void viewMenuLogic() {
        view.getHideShowMenuItem().setAccelerator(KeyCombination.keyCombination("T"));
        view.getHideShowMenuItem().setOnAction(ActionEvent -> {
            showStatsLogic();
        });

        view.getShowAliveRabbits().setOnAction(ActionEvent0 -> {
            String str = "";
            String rabbitType;
            for (Rabbit rabbit : model.getRabbitsVector()) {
                int ID = rabbit.getID();
                if (rabbit instanceof CommonRabbit)
                    rabbitType = "Common";
                else
                    rabbitType = "Albino";
                str = str.concat(rabbitType + "\t\tID: " + ID + "\tLeft to live: " + ((rabbit.getLifeTime() + rabbit.getBirthTime()) - model.gettTick()) + " seconds\n");
            }
            view.getInfoAliveRabbits().setContentText("");
            view.getInfoAliveRabbits().setContentText(str);
            view.getInfoAliveRabbits().setOnShowing(ActionEvent1 -> {
                pauseLogic();
            });
            view.getInfoAliveRabbits().setOnCloseRequest(ActionEvent2 -> {
                startLogic();
            });
            view.getInfoAliveRabbits().show();
        });

        view.getAdvancedMenuItem().setOnAction(ActionEvent3 -> {
            advancedModeLogic(model.isAdvancedMode());
        });


    }

    private void helpMenuLogic() {

    }


    private void removeRabbit(Rabbit rabbit) {
        rabbit.delete(view.getRoot());
        model.getRabbitsIdSet().remove(rabbit.ID);
        model.getRabbitsLifeTimeMap().remove(rabbit.ID);
        model.getRabbitsVector().remove(rabbit);

        if (rabbit instanceof CommonRabbit) {
            model.setCrCount(model.getCrCount() - 1);
        } else {
            model.setAlCount(model.getAlCount() - 1);
        }
    }

    private void updateRabbitsPopulation() {
        for (int i = 0; i < model.getRabbitsVector().size(); ++i) {
            Rabbit a = model.getRabbitsVector().get(i);
            if (a.getBirthTime() + a.getLifeTime() == model.gettTick()) {
                removeRabbit(a);
                i--;
            }
        }
    }

    private void advancedModeLogic(boolean isWorking) {
        model.setAdvancedMode(!model.isAdvancedMode());
        view.getSettingsAlRabbitText().setVisible(isWorking);
        view.getSettingsAlDelayText().setVisible(isWorking);
        view.getSettingsAlSpawnChanceText().setVisible(isWorking);
        view.getSettingsAlLifeTimeText().setVisible(isWorking);
        view.getAlChanceBox().setVisible(isWorking);
        view.getTextFieldAlDelay().setVisible(isWorking);
        view.getTextFieldAlLifeTime().setVisible(isWorking);

        view.getSettingsAlRabbitText().setDisable(!isWorking);
        view.getSettingsAlDelayText().setDisable(!isWorking);
        view.getSettingsAlSpawnChanceText().setDisable(!isWorking);
        view.getSettingsAlLifeTimeText().setDisable(!isWorking);
        view.getAlChanceBox().setDisable(!isWorking);
        view.getTextFieldAlDelay().setDisable(!isWorking);
        view.getTextFieldAlLifeTime().setDisable(!isWorking);


        view.getSettingsCrRabbitText().setVisible(isWorking);
        view.getSettingsCrDelayText().setVisible(isWorking);
        view.getSettingsCrSpawnChanceText().setVisible(isWorking);
        view.getCrChanceBox().setVisible(isWorking);
        view.getTextFieldCrDelay().setVisible(isWorking);
        view.getTextFieldCrLifeTime().setVisible(isWorking);
        view.getSettingsCrLifeTimeText().setVisible(isWorking);

        view.getSettingsCrRabbitText().setDisable(!isWorking);
        view.getSettingsCrDelayText().setDisable(!isWorking);
        view.getSettingsCrSpawnChanceText().setDisable(!isWorking);
        view.getCrChanceBox().setDisable(!isWorking);
        view.getTextFieldCrDelay().setDisable(!isWorking);
        view.getTextFieldCrLifeTime().setDisable(!isWorking);
        view.getSettingsCrLifeTimeText().setDisable(!isWorking);

    }
}
