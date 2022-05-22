package sample.view;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class TerminalView {
    private static TerminalView instance;

    private AnchorPane anchorPane = new AnchorPane();
    private TextArea terminalArea = new TextArea();
    private Stage stage = new Stage();
    private Scene scene;

    public static synchronized TerminalView getInstance() {
        if (instance == null) {
            instance = new TerminalView();
        }
        return instance;
    }

    public void terminalInit() {
        stage.initModality(Modality.NONE);
        stage.setTitle("Terminal - user@BigChungus: ~");
        Image image = new Image("resources/terminalIcon.png");
        stage.getIcons().add(image);
        terminalArea.setPrefWidth(525);
        terminalArea.setPrefHeight(350);
        terminalArea.setFont(Font.font("Courier New", 14));
        anchorPane.getChildren().add(terminalArea);



        scene = new Scene(anchorPane, 525, 350);
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
