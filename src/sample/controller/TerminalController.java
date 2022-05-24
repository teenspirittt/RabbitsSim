package sample.controller;

import sample.model.Model;
import sample.view.TerminalView;

import java.io.IOException;


public class TerminalController {
    private int currentCursor = 2;

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
            text = text.substring(currentCursor, text.length() - numberOnly.length() - 2);
        } else {
            text = text.substring(currentCursor, text.length());
        }
        if (text.compareTo("/reduceal\n") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().appendText(">>");
            controller.reduceAlbino(percent);
            currentCursor = terminalView.getTerminalArea().getLength();
        } else if (text.compareTo("/reducecr\n") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().appendText(">>");
            controller.reduceCommon(percent);
            currentCursor = terminalView.getTerminalArea().getLength();
        } else if (text.compareTo("/start\n") == 0) {
            terminalView.getTerminalArea().appendText(">>");
            mainController.startLogic();
            currentCursor = terminalView.getTerminalArea().getLength();
        } else if (text.compareTo("/stop\n") == 0) {
            terminalView.getTerminalArea().appendText(">>");
            currentCursor = terminalView.getTerminalArea().getLength();
            mainController.stopWithInfoLogic();
        } else if (text.compareTo("/pause\n") == 0) {
            terminalView.getTerminalArea().appendText(">>");
            currentCursor = terminalView.getTerminalArea().getLength();
            mainController.pauseLogic();
        } else if (text.compareTo("/connect\n") == 0) {
            terminalView.getTerminalArea().appendText(" Connection to 192.168.0.12... \n>>");
            currentCursor = terminalView.getTerminalArea().getLength();
            mainController.startClientThread();
        } else if (text.compareTo("/sendconfig\n") == 0) {
            terminalView.getTerminalArea().appendText(">>");
            currentCursor = terminalView.getTerminalArea().getLength();
            try {
                mainController.client.sendConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (text.compareTo("\n") == 0) {
            terminalView.getTerminalArea().appendText(">>");
            currentCursor = terminalView.getTerminalArea().getLength();
        }else {
            terminalView.getTerminalArea().appendText(" [-] Unknown command: " + text + "\n>>");
            //terminalView.getTerminalArea().appendText(" Connection to 192.168.0.12... \n>>");
            currentCursor = terminalView.getTerminalArea().getLength();
        }
    }
}
