package sample;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class TerminalView {
    private static TerminalView instance;

    private AnchorPane anchorPane = new AnchorPane();
    private TextArea terminalArea = new TextArea();
    private Stage stage = new Stage();
    private Scene scene;

    static synchronized TerminalView getInstance() {
        if (instance == null) {
            instance = new TerminalView();
        }
        return instance;
    }

    public void terminalInit() {
        stage.initModality(Modality.NONE);
        terminalArea.setPrefWidth(800);
        terminalArea.setPrefHeight(250);
        anchorPane.getChildren().add(terminalArea);

        scene = new Scene(anchorPane, 800, 250);
        stage.setScene(scene);
    }


    public TextArea getTerminalArea() {
        return terminalArea;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }
}
