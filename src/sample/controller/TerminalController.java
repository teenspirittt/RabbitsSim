package sample.controller;

import sample.model.Model;
import sample.view.TerminalView;

import java.io.IOException;


public class TerminalController {
    private int currentCursor = 2;
    private int loginFlag = 0;
    private static TerminalController instance;
    TerminalView terminalView = TerminalView.getInstance();
    Controller controller = Controller.getInstance();
    Controller mainController = Controller.getInstance();

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
        String text = terminalView.getTerminalArea().getText();
        text = text.substring(currentCursor);
        String numberOnly = text.replaceAll("[^0-9]", "");

        if (numberOnly.compareTo("") != 0) {
            percent = Integer.parseInt(numberOnly);
            text = text.substring(0, text.length() - 2 - numberOnly.length());
        }

        switch (text) {
            case "/start\n" -> startCommand();
            case "/stop\n" -> stopCommand();
            case "/pause\n" -> pauseCommand();
            case "/reducecr" -> reduceCrCommand(percent);
            case "/reduceal" -> reduceAlCommand(percent);
            case "/send object\n" -> sendObjectCommand();
            case "/connect\n" -> connectCommand();
            case "/stopConnection\n" -> stopConnectionCommand();
            case "/get object\n" -> getObjectCommand();
            case "/get online\n" -> getClientsListCommand();
            case "\n" -> endlCommand();
            default -> {
                terminalView.getTerminalArea().appendText(" [-] Unknown command: " + text + "\n>>");
                currentCursor = terminalView.getTerminalArea().getLength();
            }
        }
    }

    void startCommand() {
        terminalView.getTerminalArea().appendText(">>");
        mainController.startLogic();
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void reduceCrCommand(int percent) {
        terminalView.getTerminalArea().appendText(">>");
        controller.reduceCommon(percent);
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void reduceAlCommand(int percent) {
        terminalView.getTerminalArea().appendText(">>");
        controller.reduceAlbino(percent);
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void stopCommand() {
        terminalView.getTerminalArea().appendText(">>");
        mainController.stopWithInfoLogic();
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void pauseCommand() {
        terminalView.getTerminalArea().appendText(">>");
        mainController.pauseLogic();
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void connectCommand() {
        terminalView.getTerminalArea().appendText(" Connection to 192.168.0.12...\n>>");
        mainController.echoClient.startConnection("192.168.0.12", 8000);
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void stopConnectionCommand() {
        terminalView.getTerminalArea().appendText(">>");
        mainController.echoClient.stopConnection();
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void sendObjectCommand() {
        terminalView.getTerminalArea().appendText(">>");
        controller.propertyPackage.putProperties();
        mainController.echoClient.sendObj(controller.propertyPackage);
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void endlCommand() {
        terminalView.getTerminalArea().appendText(">>");
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void getObjectCommand() {
        terminalView.getTerminalArea().appendText(">>");
        PropertyPackage tmp = mainController.echoClient.getObj();
        tmp.setProperties();
        currentCursor = terminalView.getTerminalArea().getLength();
    }

    void getClientsListCommand() {
        String tmp = mainController.echoClient.getClientsList();
        terminalView.getTerminalArea().appendText(tmp + ">>");
        currentCursor = terminalView.getTerminalArea().getLength();

    }

}
