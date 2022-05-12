package sample;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.rabbit.AlbinoRabbit;
import sample.rabbit.CommonRabbit;


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
        String text = terminalView.getTerminalArea().getText();
        String subtext = text.substring(0, 9);
        int percent = Integer.parseInt(text.substring(9, text.length() - 1));
        if (subtext.compareTo("reduceal ") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().setText("");
            controller.reduceAlbino(percent);

        } else if (subtext.compareTo("reducecr ") == 0 && percent > 0 && percent <= 100) {
            terminalView.getTerminalArea().setText("");
            controller.reduceCommon(percent);
        } else {
            terminalView.getTerminalArea().setText("");
        }
    }

}
