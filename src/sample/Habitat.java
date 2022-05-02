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
    private final Alert incorrectRange = new Alert(Alert.AlertType.WARNING);
    private final Alert stopSimulation = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert infoAliveRabbits = new Alert(Alert.AlertType.INFORMATION);
    private final TextField textFieldAlDelay = new TextField();
    private final TextField textFieldCrDelay = new TextField();
    private final TextField textFieldAlLifeTime = new TextField();
    private final TextField textFieldCrLifeTime = new TextField();
    private final ComboBox<Integer> alChanceBox = new ComboBox<Integer>();
    private final ComboBox<Integer> crChanceBox = new ComboBox<Integer>();
    private final Group root = new Group();
    private final Stage stage = new Stage();
    private Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.web("20790878")); // Medium green
    private final MenuBar menuBar = new MenuBar();
    private final Menu runMenu = new Menu("Run");
    private final Menu editMenu = new Menu("Edit");
    private final Menu fileMenu = new Menu("File");
    private final Menu viewMenu = new Menu("View");
    private final Menu helpMenu = new Menu("Help");
    private final MenuItem startMenuItem = new MenuItem("Start");
    private final MenuItem stopMenuItem = new MenuItem("Stop");
    private final MenuItem pauseMenuItem = new MenuItem("Pause");
    private final MenuItem exitMenuItem = new MenuItem("Exit");
    private final MenuItem hideShowMenuItem = new MenuItem("Hide/Show Statistic");
    private final MenuItem showAliveRabbits = new MenuItem("Show Alive Rabbits");
    private final MenuItem helpItem = new MenuItem("? Help");
    private final MenuItem advancedMenuItem = new MenuItem("Advanced Mode");

    private Text rabbitCount = new Text();
    private Text timeText = new Text();

    public static void setInstance(Habitat instance) {
        Habitat.instance = instance;
    }

    public ToggleGroup getRadioGroup() {
        return radioGroup;
    }

    public Text getTimeText() {
        return timeText;
    }

    public void setTimeText(Text timeText) {
        this.timeText = timeText;
    }

    private final Text settingsCrRabbitText = new Text("COMMON RABBIT");
    private final Text settingsCrSpawnChanceText = new Text("Spawn Chance");
    private final Text settingsCrDelayText = new Text("Delay");
    private final Text settingsCrLifeTimeText = new Text("LifeTime");

    private final Text settingsAlRabbitText = new Text("ALBINO RABBIT");
    private final Text settingsAlSpawnChanceText = new Text("Spawn Chance");
    private final Text settingsAlDelayText = new Text("Delay");
    private final Text settingsAlLifeTimeText = new Text("LifeTime");
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

    public TextField getTextFieldAlLifeTime() {
        return textFieldAlLifeTime;
    }

    public TextField getTextFieldCrLifeTime() {
        return textFieldCrLifeTime;
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

    public Menu getViewMenu() {
        return viewMenu;
    }

    public Menu getHelpMenu() {
        return helpMenu;
    }

    public MenuItem getHelpItem() {
        return helpItem;
    }

    public MenuItem getShowAliveRabbits() {
        return showAliveRabbits;
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

    public MenuItem getAdvancedMenuItem() {
        return advancedMenuItem;
    }

    public Alert getIncorrectInput() {
        return incorrectInput;
    }

    public Text getSettingsAlDelayText() {
        return settingsAlDelayText;
    }

    public Text getSettingsAlLifeTimeText() {
        return settingsAlLifeTimeText;
    }

    public Text getSettingsAlRabbitText() {
        return settingsAlRabbitText;
    }

    public Text getSettingsAlSpawnChanceText() {
        return settingsAlSpawnChanceText;
    }

    public Text getSettingsCrDelayText() {
        return settingsCrDelayText;
    }

    public Text getSettingsCrLifeTimeText() {
        return settingsCrLifeTimeText;
    }

    public Text getSettingsCrRabbitText() {
        return settingsCrRabbitText;
    }

    public Text getSettingsCrSpawnChanceText() {
        return settingsCrSpawnChanceText;
    }

    public Alert getInfoAliveRabbits() {
        return infoAliveRabbits;
    }

    public Alert getIncorrectRange() {
        return incorrectRange;
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
        rabbitCount.setFill(Color.web("669933"));
        rabbitCount.setFont(Font.font("Cascadia Code", 13));
        rabbitCount.setX(1075);
        rabbitCount.setY(55);
        root.getChildren().add(rabbitCount);
    }

    public void initTimeText() {
        timeText.setVisible(true);
        timeText.setFill(Color.web("669933"));
        timeText.setFont(Font.font("Cascadia Code", 24));
        timeText.setX(1126);
        timeText.setY(116);
        root.getChildren().add(timeText);
    }

    public void initScene() {

        Image icon = new Image("resources/rabbitIcon.jpg");

        Rectangle recFace = new Rectangle(1039, 0, 241, 720);
        recFace.setFill(Color.web("0000004a"));

        Rectangle recInfo = new Rectangle(1061, 39, 203, 142);
        recInfo.setStroke(Color.BLACK);
        recInfo.setStrokeWidth(1);
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
        alChanceComBoxInit();
        crChanceComBoxInit();
        textAreaAlDelayInit();
        textAreaCrDelayInit();
        textFieldAlLifeTimeInit();
        textFieldCrLifeTimeInit();
        settingsMenuTextInit();
        alertsInit();
        initTimeText();
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
        hideStats.fire();
        hideStats.setFont(Font.font("Cascadia Code", 12));
        hideStats.setToggleGroup(radioGroup);
        hideStats.setLayoutX(1065);
        hideStats.setLayoutY(184);
        showStats.setFont(Font.font("Cascadia Code", 12));
        showStats.setToggleGroup(radioGroup);
        showStats.setLayoutX(1170);
        showStats.setLayoutY(184);

        root.getChildren().addAll(showStats, hideStats);
    }

    private void alChanceComBoxInit() {
        alChanceBox.setItems(chanceList);
        alChanceBox.setLayoutX(1172);
        alChanceBox.setLayoutY(386);
        alChanceBox.setPrefHeight(37);
        alChanceBox.setPrefWidth(89);
        alChanceBox.setVisibleRowCount(3);
        root.getChildren().add(alChanceBox);
    }

    private void crChanceComBoxInit() {
        crChanceBox.setItems(chanceList);
        crChanceBox.setLayoutX(1172);
        crChanceBox.setLayoutY(237);
        crChanceBox.setPrefHeight(37);
        crChanceBox.setPrefWidth(89);
        crChanceBox.setVisibleRowCount(3);
        root.getChildren().add(crChanceBox);
    }

    private void textAreaAlDelayInit() {
        textFieldAlDelay.setLayoutX(1172);
        textFieldAlDelay.setLayoutY(423);
        textFieldAlDelay.setPrefHeight(37);
        textFieldAlDelay.setPrefWidth(89);
        root.getChildren().add(textFieldAlDelay);
    }

    private void textAreaCrDelayInit() {
        textFieldCrDelay.setLayoutX(1172);
        textFieldCrDelay.setLayoutY(274);
        textFieldCrDelay.setPrefHeight(37);
        textFieldCrDelay.setPrefWidth(89);
        root.getChildren().add(textFieldCrDelay);
    }

    private void textFieldAlLifeTimeInit() {
        textFieldAlLifeTime.setLayoutX(1172);
        textFieldAlLifeTime.setLayoutY(461);
        textFieldAlLifeTime.setPrefHeight(37);
        textFieldAlLifeTime.setPrefWidth(89);
        root.getChildren().add(textFieldAlLifeTime);
    }

    private void textFieldCrLifeTimeInit() {
        textFieldCrLifeTime.setLayoutX(1172);
        textFieldCrLifeTime.setLayoutY(311);
        textFieldCrLifeTime.setPrefHeight(37);
        textFieldCrLifeTime.setPrefWidth(89);
        root.getChildren().add(textFieldCrLifeTime);
    }

    private void settingsMenuTextInit() {
        // common rabbit
        settingsCrDelayText.setFont(Font.font("Cascadia Code", 12));
        settingsCrDelayText.setLayoutX(1058);
        settingsCrDelayText.setLayoutY(297);

        settingsCrRabbitText.setFont(Font.font("Cascadia Code", 12));
        settingsCrRabbitText.setLayoutX(1108);
        settingsCrRabbitText.setLayoutY(233);

        settingsCrSpawnChanceText.setFont(Font.font("Cascadia Code", 12));
        settingsCrSpawnChanceText.setLayoutX(1058);
        settingsCrSpawnChanceText.setLayoutY(260);

        settingsCrLifeTimeText.setFont(Font.font("Cascadia Code", 12));
        settingsCrLifeTimeText.setLayoutX(1058);
        settingsCrLifeTimeText.setLayoutY(334);

        // common rabbit albino rabbit
        settingsAlDelayText.setFont(Font.font("Cascadia Code", 12));
        settingsAlDelayText.setLayoutX(1058);
        settingsAlDelayText.setLayoutY(447);

        settingsAlRabbitText.setFont(Font.font("Cascadia Code", 12));
        settingsAlRabbitText.setLayoutX(1118);
        settingsAlRabbitText.setLayoutY(382);

        settingsAlSpawnChanceText.setFont(Font.font("Cascadia Code", 12));
        settingsAlSpawnChanceText.setLayoutX(1059);
        settingsAlSpawnChanceText.setLayoutY(409);

        settingsAlLifeTimeText.setFont(Font.font("Cascadia Code", 12));
        settingsAlLifeTimeText.setLayoutX(1058);
        settingsAlLifeTimeText.setLayoutY(484);


        root.getChildren().addAll(settingsCrDelayText, settingsCrRabbitText, settingsCrSpawnChanceText,
                settingsAlDelayText, settingsAlRabbitText, settingsAlSpawnChanceText, settingsAlLifeTimeText, settingsCrLifeTimeText);
    }

    private void alertsInit() {
        incorrectInput.setTitle("Warning Alert");
        incorrectInput.setHeaderText("Incorrect Input:");
        incorrectInput.setContentText("Enter only a numeric value!");

        incorrectRange.setTitle("Warning Alert");
        incorrectRange.setHeaderText("Incorrect Input:");
        incorrectRange.setContentText("Enter values between 1 and 60!");

        stopSimulation.setTitle("Stop Simulation");
        stopSimulation.setHeaderText("Are you sure want stop simulation?\nIt will delete all rabbits.");

        infoAliveRabbits.setTitle("Alive Rabbits");
        infoAliveRabbits.setHeaderText("Alive Rabbits:");
    }

    private void menuInit() {
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, runMenu, helpMenu);
        menuBar.setPrefWidth(1280);
        runMenu.getItems().add(startMenuItem);
        runMenu.getItems().add(pauseMenuItem);
        runMenu.getItems().add(stopMenuItem);

        fileMenu.getItems().add(exitMenuItem);

        viewMenu.getItems().addAll(hideShowMenuItem, showAliveRabbits, advancedMenuItem);

        helpMenu.getItems().add(helpItem);

        root.getChildren().add(menuBar);
    }
}
