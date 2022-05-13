package sample.controller;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;
import sample.model.Model;
import sample.rabbit.AlbinoRabbit;
import sample.rabbit.CommonRabbit;
import sample.rabbit.Rabbit;
import sample.rabbitAI.AlbinoRabbitAI;
import sample.rabbitAI.CommonRabbitAI;
import sample.view.Habitat;


import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {
    private static Controller instance;


    Model model = Model.getInstance();
    Habitat view = Habitat.getInstance();

    final CommonRabbitAI commonRabbitAI = new CommonRabbitAI(model.getRabbitsVector());
    final AlbinoRabbitAI albinoRabbitAI = new AlbinoRabbitAI(model.getRabbitsVector());
    TerminalController terminalController;
    LoadSaveController loadSaveController = new LoadSaveController();
    private final Random random = new Random();
    private Timer timer = new Timer();

    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void initController(Habitat view) {
        this.view = view;
        terminalController = TerminalController.getInstance();
        keyBinds();
        startAIThreads();
        threadEditModeLogic(model.isAdvancedMode());
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
        stopAlMoveCheckBoxLogic();
        stopCrMoveCheckBoxLogic();
        alThreadPriorityComBoxLogic();
        crThreadPriorityComBoxLogic();
        mainThreadPriorityComBoxLogic();
        fileMenuLogic();
        runMenuLogic();
        viewMenuLogic();
        helpMenuLogic();
        editMenuLogic();
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
            model.getRabbitsVector().get(i).setBirthX(random.nextInt(view.getSceneWidth() - 339));
            model.getRabbitsVector().get(i).setBirthY(20 + random.nextInt(view.getSceneHeight() - 77 - 20)); // 77-rabbit height, 20-menu height
            model.getRabbitsVector().get(i).setPosX(model.getRabbitsVector().get(i).getBirthX());
            model.getRabbitsVector().get(i).setPosY(model.getRabbitsVector().get(i).getBirthY());
            model.getRabbitsVector().get(i).spawn(model.getRabbitsVector().get(i).getBirthX(), model.getRabbitsVector().get(i).getBirthY(), view.getRoot());

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
                    updateTime();
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
                case I -> threadEditModeLogic(model.isAdvancedMode());
            }
        });
    }

    private void startLogic() {
        if (!model.isTimerWorking()) {
            timer = startTimer();
            view.getStopMoveAlCheckBox().setDisable(false);
            view.getStopMoveCrCheckBox().setDisable(false);
            view.getStartButton().setDisable(true);
            view.getStopButton().setDisable(false);
            view.getPauseButton().setDisable(false);
            view.getStartMenuItem().setDisable(true);
            view.getStopMenuItem().setDisable(false);
            view.getPauseMenuItem().setDisable(false);
            model.setTimerWorking(true);
            view.getPauseButton().setVisible(true);
            view.getStartButton().setVisible(false);
            startAlMovement();
            startCrMovement();
        }
    }

    private void pauseLogic() {
        timer.cancel();
        pauseAlMovement();
        pauseCrMovement();
        view.getStopMoveAlCheckBox().setDisable(true);
        view.getStopMoveCrCheckBox().setDisable(true);
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
                    "\nClassic lifetime: " + model.getCrLifeTime() +
                    "\nAlbino chance: " + model.getAlChance() +
                    "\nAlbino delay: " + model.getAlTime() +
                    "\nAlbino lifetime: " + model.getAlLifeTime());
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
        view.getStopMoveAlCheckBox().setDisable(true);
        view.getStopMoveCrCheckBox().setDisable(true);
        view.getStopMoveCrCheckBox().setSelected(false);
        view.getStopMoveAlCheckBox().setSelected(false);
        model.setTimerWorking(false);
        model.getRabbitsVector().clear();
        model.resetStats();
        updateStats();
        updateTime();
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
        view.getTimeText().setVisible(!model.isStatsVisible());
        if (model.isStatsVisible())
            view.getShowStats().fire();
        else
            view.getHideStats().fire();
    }


    private void buttonStartLogic() {
        view.getStartButton().setOnAction(ActionEvent -> startLogic());
    }

    private void buttonPauseLogic() {
        view.getPauseButton().setOnAction(ActionEvent -> pauseLogic());
    }

    private void buttonStopLogic() {
        view.getStopButton().setOnAction(ActionEvent -> stopWithInfoLogic());
    }


    private void updateTime() {
        view.getTimeText().setFont(Font.font("Cascadia Code", 17));
        view.getTimeText().setText("Time: " + model.gettTick());
    }

    private void updateStats() {
        view.getRabbitCount().setText("Time: " + model.gettTick() +
                "\nClassic Rabbits: " + model.getCrCount() +
                "\nAlbino Rabbits: " + model.getAlCount() +
                "\nClassic chance: " + model.getCrChance() +
                "\nClassic lifeTime: " + model.getCrLifeTime() +
                "\nClassic birthDelay: " + model.getCrTime() +
                "\nAlbino chance: " + model.getAlChance() +
                "\nAlbino lifeTime: " + model.getAlLifeTime() +
                "\nAlbino birthDelay: " + model.getAlTime());
    }

    private void radButtonHideStatsLogic() {
        view.getHideStats().setOnAction(ActionEvent -> {
            model.setStatsVisible(false);
            view.getRabbitCount().setVisible(model.isStatsVisible());
            view.getTimeText().setVisible(!model.isStatsVisible());
        });
    }

    private void radButtonShowStatsLogic() {
        view.getShowStats().setOnAction(ActionEvent -> {
            model.setStatsVisible(true);
            view.getRabbitCount().setVisible(model.isStatsVisible());
            view.getTimeText().setVisible(!model.isStatsVisible());
            updateStats();
            updateTime();
        });
    }

    private void stopAlMoveCheckBoxLogic() {
        view.getStopMoveAlCheckBox().setOnAction(ActionEvent -> {
            stopAlMovementLogic();
        });
    }

    private void stopCrMoveCheckBoxLogic() {
        view.getStopMoveCrCheckBox().setOnAction(ActionEvent -> {
            stopCrMovementLogic();
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

    private void alThreadPriorityComBoxLogic() {
        view.getAlThreadPriorityComBox().setValue(model.getAlThreadPriority());
        view.getAlThreadPriorityComBox().setOnAction(ActionEvent -> {
            model.setAlThreadPriority(view.getAlThreadPriorityComBox().getValue());
        });
        albinoRabbitAI.setPriority(model.getAlThreadPriority());
    }

    private void crThreadPriorityComBoxLogic() {
        view.getCrThreadPriorityComBox().setValue(model.getCrThreadPriority());
        view.getCrThreadPriorityComBox().setOnAction(ActionEvent -> {
            model.setCrThreadPriority(view.getCrThreadPriorityComBox().getValue());
        });
        commonRabbitAI.setPriority(model.getCrThreadPriority());
    }

    private void mainThreadPriorityComBoxLogic() {
        view.getMainThreadPriorityComBox().setValue(model.getMainThreadPriority());
        view.getMainThreadPriorityComBox().setOnAction(ActionEvent -> {
            model.setMainThreadPriority(view.getMainThreadPriorityComBox().getValue());
        });
        Thread.currentThread().setPriority(model.getMainThreadPriority());
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
        view.getExitMenuItem().setOnAction(ActionEvent1 -> System.exit(0));
        view.getSaveMenuItem().setOnAction(ActionEvent2 -> {
            loadSaveController.saveRabbits();
        });

        view.getLoadMenuItem().setOnAction(ActionEvent3 -> {
            loadSaveController.loadRabbits();
        });
    }

    private void runMenuLogic() {
        view.getStartMenuItem().setAccelerator(KeyCombination.keyCombination("B"));
        view.getStartMenuItem().setOnAction(ActionEvent -> startLogic());
        view.getPauseMenuItem().setAccelerator(KeyCombination.keyCombination("E"));
        view.getPauseMenuItem().setOnAction(ActionEvent -> pauseLogic());
        view.getStopMenuItem().setAccelerator(KeyCombination.keyCombination("S"));
        view.getStopMenuItem().setOnAction(ActionEvent -> stopWithInfoLogic());
    }

    private void viewMenuLogic() {
        view.getHideShowMenuItem().setAccelerator(KeyCombination.keyCombination("T"));
        view.getHideShowMenuItem().setOnAction(ActionEvent -> showStatsLogic());

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
            view.getInfoAliveRabbits().setOnShowing(ActionEvent1 -> pauseLogic());
            view.getInfoAliveRabbits().setOnCloseRequest(ActionEvent2 -> startLogic());
            view.getInfoAliveRabbits().show();
        });
        view.getThreadEditMenuItem().setOnAction(ActionEvent3 -> threadEditModeLogic(model.isAdvancedMode()));
    }

    private void helpMenuLogic() {

    }

    private void editMenuLogic() {
        view.getTerminal().setOnAction(ActionEvent -> {
            showTerminal();
        });
    }


    private void removeRabbit(Rabbit rabbit) {
        rabbit.delete(view.getRoot());
        model.getRabbitsIdSet().remove(rabbit.getID());
        model.getRabbitsLifeTimeMap().remove(rabbit.getID());
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

    private void threadEditModeLogic(boolean isWorking) {
        model.setAdvancedMode(!model.isAdvancedMode());
        view.getStopMoveAlText().setVisible(!isWorking);
        view.getStopMoveCrText().setVisible(!isWorking);
        view.getAlThreadPriorityComBox().setVisible(!isWorking);
        view.getCrThreadPriorityComBox().setVisible(!isWorking);
        view.getMainThreadPriorityComBox().setVisible(!isWorking);
        view.getStopMoveAlCheckBox().setVisible(!isWorking);
        view.getStopMoveCrCheckBox().setVisible(!isWorking);
        view.getThreadPriorityAlText().setVisible(!isWorking);
        view.getThreadPriorityCrText().setVisible(!isWorking);
        view.getThreadPriorityMainText().setVisible(!isWorking);
    }

    private void startAIThreads() {
        commonRabbitAI.start();
        albinoRabbitAI.start();
    }

    private void pauseAlMovement() {
        albinoRabbitAI.pause();
    }

    private void pauseCrMovement() {
        commonRabbitAI.pause();
    }

    private void startAlMovement() {
        if (view.getStartButton().isDisabled())
            albinoRabbitAI.unpause();
    }

    private void startCrMovement() {
        if (view.getStartButton().isDisabled())
            commonRabbitAI.unpause();
    }

    private void stopAlMovementLogic() {
        if (albinoRabbitAI.isPaused())
            startAlMovement();
        else
            pauseAlMovement();
    }

    private void stopCrMovementLogic() {
        if (commonRabbitAI.isPaused())
            startCrMovement();
        else
            pauseCrMovement();
    }

    private void showTerminal() {
        terminalController.showTerminal();
    }

    public void reduceAlbino(int reducePercent) {
        int tmp = (reducePercent * model.getAlCount()) / 100;
        for (int i = 0; i < model.getRabbitsVector().size(); ++i) {
            if (tmp == 0) {
                break;
            }
            if (model.getRabbitsVector().get(i) instanceof AlbinoRabbit) {
                removeRabbit(model.getRabbitsVector().get(i));
                tmp--;
                i--;
            }
        }
    }

    public void reduceCommon(int reducePercent) {
        int tmp = (reducePercent * model.getCrCount()) / 100;
        for (int i = 0; i < model.getRabbitsVector().size(); ++i) {
            if (tmp == 0) {
                break;
            }
            if (model.getRabbitsVector().get(i) instanceof CommonRabbit) {
                removeRabbit(model.getRabbitsVector().get(i));
                tmp--;
                i--;
            }
        }
    }
}
