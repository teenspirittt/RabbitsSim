package sample;

import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Habitat {

    private static Habitat instance;

    private final int sceneWidth = 1280;
    private final int sceneHeight = 720;
    Button stopButton = new Button("Stop");
    Button startButton = new Button("Start");
    Button pauseButton = new Button("Pause");
    private final Group root = new Group();
    private final Stage stage = new Stage();
    Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.DARKGREEN);
    private Text rabbitCount = new Text();


    static synchronized Habitat getInstance() {
        if (instance == null) {
            instance = new Habitat();
        }
        return instance;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }


    public Group getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Text getRabbitCount() {
        return rabbitCount;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public void setRabbitCount(Text rabbitCount) {
        this.rabbitCount = rabbitCount;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void initStats() {
        rabbitCount.setFont(Font.font("Montserrat", 22));
        rabbitCount.setX(1060);
        rabbitCount.setY(50);
        root.getChildren().add(rabbitCount);
    }

    public void initScene() {
        Image icon = new Image("resources/rabbitIcon.jpg");
        Rectangle recForInfo = new Rectangle(1050, 0, 1280, 720);
        recForInfo.setOpacity(0.1);
        root.getChildren().add(recForInfo);
        stage.getIcons().add(icon);
        stage.setTitle("Rabbits");
        stage.setScene(scene);
        buttonPauseInit();
        buttonStartInit();
        buttonStopInit();
        initStats();
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    private void buttonStartInit() {
        startButton.setLayoutX(1118);
        startButton.setLayoutY(150);
        //buttonStartLogic();
        startButton.setDisable(false);
        root.getChildren().add(startButton);
    }

    private void buttonStopInit() {
        stopButton.setLayoutX(1174);
        stopButton.setLayoutY(150);
        //buttonStopLogic();
        stopButton.setDisable(true);
        root.getChildren().add(stopButton);
    }

    private void buttonPauseInit() {
        pauseButton.setLayoutX(1118);
        pauseButton.setLayoutY(150);
        //buttonPauseLogic();
        pauseButton.setDisable(false);
        pauseButton.setVisible(false);
        root.getChildren().add(pauseButton);
    }

}
