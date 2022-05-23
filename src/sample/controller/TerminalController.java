package sample.controller;

import sample.model.Model;
import sample.view.TerminalView;


public class TerminalController {

    private static TerminalController instance;
    TerminalView terminalView = TerminalView.getInstance();
    Model model = Model.getInstance();
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

    static synchronized TerminalController getInstance() {
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
        String numberOnly = text.replaceAll("[^0-9]", "");
        if (numberOnly.compareTo("") != 0) {
            percent = Integer.parseInt(numberOnly);
            text = text.substring(0, text.length() - numberOnly.length() - 2);
        }

        if (text.compareTo("reduceal") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().setText("");
            controller.reduceAlbino(percent);

        } else if (text.compareTo("reducecr") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().setText("");
            controller.reduceCommon(percent);
        } else {
            terminalView.getTerminalArea().setText("");
        }
    }
}
