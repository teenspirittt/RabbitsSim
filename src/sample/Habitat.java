package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.*;
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
    private Button stopButton = new Button("STOP");
    private Button startButton = new Button("START");
    private Button pauseButton = new Button("PAUSE");
    private ToggleGroup radioGroup = new ToggleGroup();
    private RadioButton showStats = new RadioButton("Show stats");
    private RadioButton hideStats = new RadioButton("Hide stats");
    private ComboBox<Integer> alChanceBox = new ComboBox<Integer>();
    private ComboBox<Integer> crChanceBox = new ComboBox<Integer>();
    private final Group root = new Group();
    private final Stage stage = new Stage();
    Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.web("20790878"));
    private Text rabbitCount = new Text();
    private ObservableList<Integer> chanceList = FXCollections.observableArrayList(100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 0);


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

    public RadioButton getHideStats() {
        return hideStats;
    }

    public ComboBox<Integer> getAlChanceBox() {
        return alChanceBox;
    }

    public ComboBox<Integer> getCrChanceBox() {
        return crChanceBox;
    }

    public RadioButton getShowStats() {
        return showStats;
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

    public ObservableList<Integer> getChanceList() {
        return chanceList;
    }

    public void setRabbitCount(Text rabbitCount) {
        this.rabbitCount = rabbitCount;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void initStats() {
        rabbitCount.setVisible(false);
        rabbitCount.setFont(Font.font("Cascadia Code", 17));
        rabbitCount.setX(1074);
        rabbitCount.setY(68);
        root.getChildren().add(rabbitCount);
    }

    public void initScene() {

        Image icon = new Image("resources/rabbitIcon.jpg");

        Rectangle recFace = new Rectangle(1039, 0, 241, 720);
        recFace.setFill(Color.web("0000004a"));

        Rectangle recInfo = new Rectangle(1061, 39, 203, 132);
        recInfo.setFill(Color.web("00000086"));

        root.getChildren().addAll(recFace, recInfo);
        stage.getIcons().add(icon);
        stage.setTitle("Rabbits");
        stage.setScene(scene);
        stage.setResizable(false);
        buttonPauseInit();
        buttonStartInit();
        buttonStopInit();
        radioButtonsShowStatsInit();
        alChanceListInit();
        crChanceListInit();
        initStats();
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    private void buttonStartInit() {
        startButton.setFont(Font.font("Cascadia Code", 38));
        startButton.setPrefSize(203, 84);
        startButton.setLayoutX(1058);
        startButton.setLayoutY(591);
        startButton.setDisable(false);
        root.getChildren().add(startButton);
    }

    private void buttonStopInit() {
        stopButton.setFont(Font.font("Cascadia Code", 15));
        stopButton.setPrefSize(203, 31);
        stopButton.setLayoutX(1058);
        stopButton.setLayoutY(675);
        stopButton.setDisable(true);
        root.getChildren().add(stopButton);
    }

    private void buttonPauseInit() {
        pauseButton.setFont(Font.font("Cascadia Code", 38));
        pauseButton.setPrefSize(203, 84);
        pauseButton.setLayoutX(1058);
        pauseButton.setLayoutY(591);
        pauseButton.setDisable(false);
        pauseButton.setVisible(false);
        root.getChildren().add(pauseButton);
    }

    private void radioButtonsShowStatsInit() {
        hideStats.setFont(Font.font("Cascadia Code", 12));
        hideStats.setToggleGroup(radioGroup);
        hideStats.setLayoutX(1065);
        hideStats.setLayoutY(171);

        showStats.setFont(Font.font("Cascadia Code", 12));
        showStats.setToggleGroup(radioGroup);
        showStats.setLayoutX(1170);
        showStats.setLayoutY(171);

        root.getChildren().addAll(showStats, hideStats);
    }

    private void alChanceListInit() {
        alChanceBox.setValue(100);
        alChanceBox.setItems(chanceList);
        alChanceBox.setLayoutX(1059);
        alChanceBox.setLayoutY(203);
        alChanceBox.setVisibleRowCount(3);
        root.getChildren().add(alChanceBox);
    }

    private void crChanceListInit() {
        crChanceBox.setValue(100);
        crChanceBox.setItems(chanceList);
        crChanceBox.setLayoutX(1059);
        crChanceBox.setLayoutY(252);
        crChanceBox.setVisibleRowCount(3);
        root.getChildren().add(crChanceBox);
    }


}
