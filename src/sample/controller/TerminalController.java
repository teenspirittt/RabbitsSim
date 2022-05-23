package sample.controller;

import sample.model.Model;
import sample.view.TerminalView;

import java.io.IOException;


public class TerminalController {

    private static TerminalController instance;
    TerminalView terminalView = TerminalView.getInstance();
    Controller controller = Controller.getInstance();

    public void showTerminal() {
        terminalView.getStage().show();
        terminalView.getTerminalArea().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case ESCAPE -> hideTerminal();
                case ENTER -> enterCommand();
            }
        });
    }

    public static synchronized TerminalController getInstance() {
        if (instance == null) {
            instance = new TerminalController();
        }
        return instance;
    }

    public void hideTerminal() {
        terminalView.getStage().hide();
    }

    public void enterCommand() {
        int percent = 0;
        Controller mainController = Controller.getInstance();
        String text = terminalView.getTerminalArea().getText();
        String numberOnly = text.replaceAll("[^0-9]", "");
        if (numberOnly.compareTo("") != 0) {
            percent = Integer.parseInt(numberOnly);
            System.out.println(numberOnly);
            System.out.println(percent);
            text = text.substring(0, text.length() - numberOnly.length() - 2);
        } else {
            text = text.substring(0, text.length() - 1);
        }
        if (text.compareTo("/reduceal") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().setText("");
            controller.reduceAlbino(percent);

        } else if (text.compareTo("/reducecr") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().setText("");
            controller.reduceCommon(percent);
        } else if (text.compareTo("/start") == 0) {
            terminalView.getTerminalArea().setText("");
            mainController.startLogic();
        } else if (text.compareTo("/stop") == 0) {
            terminalView.getTerminalArea().setText("");
            mainController.stopWithInfoLogic();
        } else if (text.compareTo("/pause") == 0) {
            terminalView.getTerminalArea().setText("");
            mainController.pauseLogic();
        } else if (text.compareTo("/connect") == 0) {
            terminalView.getTerminalArea().setText("");
            mainController.startClientThread();
        } else if (text.compareTo("/sendconfig") == 0) {
            terminalView.getTerminalArea().setText("");
            try {
                mainController.client.sendConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            terminalView.getTerminalArea().setText("");
        }
    }
}
