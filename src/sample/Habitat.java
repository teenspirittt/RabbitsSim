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
    private final Button stopButton = new Button("STOP");
    private final Button startButton = new Button("START");
    private final Button pauseButton = new Button("PAUSE");
    private final ToggleGroup radioGroup = new ToggleGroup();
    private final RadioButton showStats = new RadioButton("Show stats");
    private final RadioButton hideStats = new RadioButton("Hide stats");
    private final Alert incorrectInput = new Alert(Alert.AlertType.WARNING);
    private final Alert stopSimulation = new Alert(Alert.AlertType.CONFIRMATION);
    private final TextField textFieldAlDelay = new TextField();
    private final TextField textFieldCrDelay = new TextField();
    private final ComboBox<Integer> alChanceBox = new ComboBox<Integer>();
    private final ComboBox<Integer> crChanceBox = new ComboBox<Integer>();
    private final Group root = new Group();
    private final Stage stage = new Stage();
    private Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.web("20790878"));
    private final MenuBar menuBar = new MenuBar();
    private final Menu runMenu = new Menu("Run");
    private final Menu editMenu = new Menu("Edit");
    private final Menu fileMenu = new Menu("File");
    private final Menu viewMenu = new Menu("View");
    private final MenuItem startMenuItem = new MenuItem("Start");
    private final MenuItem stopMenuItem = new MenuItem("Stop");
    private final MenuItem pauseMenuItem = new MenuItem("Pause");
    private final MenuItem exitMenuItem = new MenuItem("Exit");
    private final MenuItem hideShowMenuItem = new MenuItem("Hide/Show Statistic");

    private Text rabbitCount = new Text();


    private final Text settingsCrRabbitText = new Text("COMMON RABBIT");
    private final Text settingsCrSpawnChanceText = new Text("Spawn Chance");
    private final Text settingsCrDelayText = new Text("Delay");

    private final Text settingsAlRabbitText = new Text("ALBINO RABBIT");
    private final Text settingsAlSpawnChanceText = new Text("Spawn Chance");
    private final Text settingsAlDelayText = new Text("Delay");

    private final ObservableList<Integer> chanceList = FXCollections.observableArrayList(100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 0);


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

    public TextField getTextFieldAlDelay() {
        return textFieldAlDelay;
    }

    public TextField getTextFieldCrDelay() {
        return textFieldCrDelay;
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

    public Menu getEditMenu() {
        return editMenu;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public Menu getRunMenu() {
        return runMenu;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getPauseMenuItem() {
        return pauseMenuItem;
    }

    public MenuItem getStartMenuItem() {
        return startMenuItem;
    }

    public MenuItem getStopMenuItem() {
        return stopMenuItem;
    }

    public MenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public MenuItem getHideShowMenuItem() {
        return hideShowMenuItem;
    }

    public Alert getIncorrectInput() {
        return incorrectInput;
    }

    public Alert getStopSimulation() {
        return stopSimulation;
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
        rabbitCount.setFont(Font.font("Cascadia Code", 13));
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
        textAreaAlDelayInit();
        textAreaCrDelayInit();
        settingsMenuTextInit();
        alertsInit();
        menuInit();

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
        alChanceBox.setLayoutX(1172);
        alChanceBox.setLayoutY(323);
        alChanceBox.setPrefHeight(37);
        alChanceBox.setPrefWidth(89);
        alChanceBox.setVisibleRowCount(3);
        root.getChildren().add(alChanceBox);
    }

    private void crChanceListInit() {
        crChanceBox.setValue(100);
        crChanceBox.setItems(chanceList);
        crChanceBox.setLayoutX(1172);
        crChanceBox.setLayoutY(222);
        crChanceBox.setPrefHeight(37);
        crChanceBox.setPrefWidth(89);
        crChanceBox.setVisibleRowCount(3);
        root.getChildren().add(crChanceBox);
    }

    private void textAreaAlDelayInit() {
        textFieldAlDelay.setLayoutX(1172);
        textFieldAlDelay.setLayoutY(360);
        textFieldAlDelay.setPrefHeight(37);
        textFieldAlDelay.setPrefWidth(89);
        root.getChildren().add(textFieldAlDelay);
    }

    private void textAreaCrDelayInit() {
        textFieldCrDelay.setLayoutX(1172);
        textFieldCrDelay.setLayoutY(259);
        textFieldCrDelay.setPrefHeight(37);
        textFieldCrDelay.setPrefWidth(89);
        root.getChildren().add(textFieldCrDelay);
    }

    private void settingsMenuTextInit() {

        settingsCrDelayText.setFont(Font.font("Cascadia Code", 12));
        settingsCrDelayText.setLayoutX(1058);
        settingsCrDelayText.setLayoutY(282);

        settingsCrRabbitText.setFont(Font.font("Cascadia Code", 12));
        settingsCrRabbitText.setLayoutX(1108);
        settingsCrRabbitText.setLayoutY(218);
        settingsCrSpawnChanceText.setFont(Font.font("Cascadia Code", 12));
        settingsCrSpawnChanceText.setLayoutX(1058);
        settingsCrSpawnChanceText.setLayoutY(245);

        settingsAlDelayText.setFont(Font.font("Cascadia Code", 12));
        settingsAlDelayText.setLayoutX(1058);
        settingsAlDelayText.setLayoutY(383);
        settingsAlRabbitText.setFont(Font.font("Cascadia Code", 12));
        settingsAlRabbitText.setLayoutX(1108);
        settingsAlRabbitText.setLayoutY(319);
        settingsAlSpawnChanceText.setFont(Font.font("Cascadia Code", 12));
        settingsAlSpawnChanceText.setLayoutX(1059);
        settingsAlSpawnChanceText.setLayoutY(346);


        root.getChildren().addAll(settingsCrDelayText, settingsCrRabbitText, settingsCrSpawnChanceText,
                settingsAlDelayText, settingsAlRabbitText, settingsAlSpawnChanceText);
    }

    private void alertsInit() {
        incorrectInput.setTitle("Warning Alert");
        incorrectInput.setHeaderText("Incorrect Input:");
        incorrectInput.setContentText("Enter only a numeric value!");

        stopSimulation.setTitle("Stop Simulation");
        stopSimulation.setHeaderText("Are you sure want stop simulation?\nIt will delete all rabbits.");
    }

    private void menuInit() {
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, runMenu);
        menuBar.setPrefWidth(1280);
        runMenu.getItems().add(startMenuItem);
        runMenu.getItems().add(pauseMenuItem);
        runMenu.getItems().add(stopMenuItem);

        fileMenu.getItems().add(exitMenuItem);

        viewMenu.getItems().add(hideShowMenuItem);

        root.getChildren().add(menuBar);
    }

}
