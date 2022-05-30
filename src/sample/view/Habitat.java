package sample.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.ConfigHandler;



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
    private final CheckBox stopMoveAlCheckBox = new CheckBox();
    private final CheckBox stopMoveCrCheckBox = new CheckBox();
    private final Alert incorrectInput = new Alert(Alert.AlertType.WARNING);
    private final Alert incorrectRange = new Alert(Alert.AlertType.WARNING);
    private final Alert stopSimulation = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert infoAliveRabbits = new Alert(Alert.AlertType.INFORMATION);
    private final TextField textFieldAlDelay = new TextField();
    private final TextField textFieldCrDelay = new TextField();
    private final TextField textFieldAlLifeTime = new TextField();
    private final TextField textFieldCrLifeTime = new TextField();

    private final ComboBox<Integer> alChanceBox = new ComboBox<>();
    private final ComboBox<Integer> crChanceBox = new ComboBox<>();
    private final ComboBox<Integer> alThreadPriorityComBox = new ComboBox<>();
    private final ComboBox<Integer> crThreadPriorityComBox = new ComboBox<>();
    private final ComboBox<Integer> mainThreadPriorityComBox = new ComboBox<>();
    private final Group root = new Group();
    private final Stage stage = new Stage();
    private final Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.web("20790878")); // Medium green
    private final MenuBar menuBar = new MenuBar();
    private final Menu runMenu = new Menu("Run");
    private final Menu editMenu = new Menu("Edit");
    private final Menu fileMenu = new Menu("File");
    private final Menu viewMenu = new Menu("View");
    private final Menu helpMenu = new Menu("Help");
    private final Menu saveSubMenu = new Menu("Save");
    private final Menu loadSubMenu = new Menu("Load");
    private final MenuItem startMenuItem = new MenuItem("Start");
    private final MenuItem stopMenuItem = new MenuItem("Stop");
    private final MenuItem pauseMenuItem = new MenuItem("Pause");
    private final MenuItem exitMenuItem = new MenuItem("Exit");
    private final MenuItem saveMenuItem = new MenuItem("Save in .dat");
    private final MenuItem loadMenuItem = new MenuItem("Load from .dat");
    private final MenuItem hideShowMenuItem = new MenuItem("Hide/Show Statistic");
    private final MenuItem showAliveRabbits = new MenuItem("Show Alive Rabbits");
    private final MenuItem helpItem = new MenuItem("? Help");
    private final MenuItem threadEditMenuItem = new MenuItem("Thread Edit Mode");
    private final MenuItem terminal = new MenuItem("Terminal");
    private final MenuItem saveDBMenuItem = new MenuItem("Save in DB");
    private final MenuItem loadDBMenuItem = new MenuItem("Load from DB");

    private final Text rabbitCount = new Text();
    private final Text timeText = new Text();


    public Text getTimeText() {
        return timeText;
    }

    private final Text settingsCrRabbitText = new Text("COMMON RABBIT");
    private final Text settingsCrSpawnChanceText = new Text("Spawn Chance");
    private final Text settingsCrDelayText = new Text("Delay");
    private final Text settingsCrLifeTimeText = new Text("LifeTime");

    private final Text settingsAlRabbitText = new Text("ALBINO RABBIT");
    private final Text settingsAlSpawnChanceText = new Text("Spawn Chance");
    private final Text settingsAlDelayText = new Text("Delay");
    private final Text settingsAlLifeTimeText = new Text("LifeTime");
    private final Text stopMoveAlText = new Text("Stop movement for Albino");
    private final Text stopMoveCrText = new Text("Stop movement for Common");
    private final Text threadPriorityAlText = new Text("ART");
    private final Text threadPriorityCrText = new Text("CRT");
    private final Text threadPriorityMainText = new Text("MT");
    private final ObservableList<Integer> chanceList = FXCollections.observableArrayList(100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 0);
    private final ObservableList<Integer> threadPriorityList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static ConfigHandler configHandler = new ConfigHandler();

    public static synchronized Habitat getInstance() {
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

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getSaveDBMenuItem() {
        return saveDBMenuItem;
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

    public static void setInstance(Habitat instance) {
        Habitat.instance = instance;
    }

    public ComboBox<Integer> getAlThreadPriorityComBox() {
        return alThreadPriorityComBox;
    }

    public ComboBox<Integer> getCrThreadPriorityComBox() {
        return crThreadPriorityComBox;
    }

    public ComboBox<Integer> getMainThreadPriorityComBox() {
        return mainThreadPriorityComBox;
    }

    public ToggleGroup getRadioGroup() {
        return radioGroup;
    }

    public Text getThreadPriorityAlText() {
        return threadPriorityAlText;
    }

    public Text getThreadPriorityCrText() {
        return threadPriorityCrText;
    }

    public Text getThreadPriorityMainText() {
        return threadPriorityMainText;
    }

    public Group getRoot() {
        return root;
    }

    public MenuItem getLoadDBMenuItem() {
        return loadDBMenuItem;
    }

    public Scene getScene() {
        return scene;
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

    public MenuItem getShowAliveRabbits() {
        return showAliveRabbits;
    }

    public Alert getIncorrectRange() {
        return incorrectRange;
    }

    public Menu getEditMenu() {
        return editMenu;
    }

    public MenuItem getTerminal() {
        return terminal;
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

    public MenuItem getThreadEditMenuItem() {
        return threadEditMenuItem;
    }

    public Alert getIncorrectInput() {
        return incorrectInput;
    }

    public Text getSettingsAlDelayText() {
        return settingsAlDelayText;
    }

    public Stage getStage() {
        return stage;
    }

    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public MenuItem getLoadMenuItem() {
        return loadMenuItem;
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

    public Text getStopMoveAlText() {
        return stopMoveAlText;
    }

    public Text getStopMoveCrText() {
        return stopMoveCrText;
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

    public Alert getStopSimulation() {
        return stopSimulation;
    }

    public CheckBox getStopMoveAlCheckBox() {
        return stopMoveAlCheckBox;
    }

    public CheckBox getStopMoveCrCheckBox() {
        return stopMoveCrCheckBox;
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
        TerminalView terminalView = TerminalView.getInstance();
        Image icon = new Image("resources/images/mainIcon.jpg");

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
        terminalView.terminalInit();
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
        stopMoveAlCheckBoxInit();
        stopMoveCrCheckBoxInit();
        crThreadPriorityComBoxInit();
        mainThreadPriorityComBoxInit();
        alThreadPriorityComBoxInit();
        menuInit();

        initStats();
        stage.setOnCloseRequest(windowEvent -> {
            configHandler.saveConfig();
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    private void buttonStartInit() {
        startButton.setFont(Font.font("Cascadia Code", 38));
        startButton.setPrefSize(203, 75);
        startButton.setLayoutX(1058);
        startButton.setLayoutY(600);
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
        pauseButton.setPrefSize(203, 75);
        pauseButton.setLayoutX(1058);
        pauseButton.setLayoutY(600);
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
        alChanceBox.setLayoutY(376);
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

    private void crThreadPriorityComBoxInit() {
        crThreadPriorityComBox.setVisible(false);
        crThreadPriorityComBox.setLayoutX(1194);
        crThreadPriorityComBox.setLayoutY(550);
        crThreadPriorityComBox.setPrefWidth(67);
        crThreadPriorityComBox.setPrefHeight(20);
        crThreadPriorityComBox.setItems(threadPriorityList);
        crThreadPriorityComBox.setVisibleRowCount(1);
        root.getChildren().add(crThreadPriorityComBox);
    }

    private void alThreadPriorityComBoxInit() {
        alThreadPriorityComBox.setVisible(false);
        alThreadPriorityComBox.setLayoutX(1126);
        alThreadPriorityComBox.setLayoutY(550);
        alThreadPriorityComBox.setPrefWidth(67);
        alThreadPriorityComBox.setPrefHeight(20);
        alThreadPriorityComBox.setItems(threadPriorityList);
        alThreadPriorityComBox.setVisibleRowCount(1);
        root.getChildren().add(alThreadPriorityComBox);
    }

    private void mainThreadPriorityComBoxInit() {
        mainThreadPriorityComBox.setVisible(false);
        mainThreadPriorityComBox.setLayoutX(1058);
        mainThreadPriorityComBox.setLayoutY(550);
        mainThreadPriorityComBox.setPrefWidth(67);
        mainThreadPriorityComBox.setPrefHeight(20);
        mainThreadPriorityComBox.setItems(threadPriorityList);
        mainThreadPriorityComBox.setVisibleRowCount(1);
        root.getChildren().add(mainThreadPriorityComBox);
    }


    private void stopMoveAlCheckBoxInit() {
        stopMoveAlCheckBox.setLayoutX(1242);
        stopMoveAlCheckBox.setLayoutY(527);
        stopMoveAlCheckBox.setVisible(false);
        stopMoveAlCheckBox.setDisable(true);
        root.getChildren().add(stopMoveAlCheckBox);
    }

    private void stopMoveCrCheckBoxInit() {
        stopMoveCrCheckBox.setLayoutX(1242);
        stopMoveCrCheckBox.setLayoutY(502);
        stopMoveCrCheckBox.setVisible(false);
        stopMoveCrCheckBox.setDisable(true);
        root.getChildren().add(stopMoveCrCheckBox);
    }

    private void textAreaAlDelayInit() {
        textFieldAlDelay.setLayoutX(1172);
        textFieldAlDelay.setLayoutY(413);
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
        textFieldAlLifeTime.setLayoutY(451);
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

        stopMoveCrText.setFont(Font.font("Cascadia Code", 12));
        stopMoveCrText.setLayoutX(1058);
        stopMoveCrText.setLayoutY(515);

        threadPriorityCrText.setFont(Font.font("Cascadia Code", 12));
        threadPriorityCrText.setLayoutX(1217);
        threadPriorityCrText.setLayoutY(588);

        // albino rabbit
        settingsAlDelayText.setFont(Font.font("Cascadia Code", 12));
        settingsAlDelayText.setLayoutX(1058);
        settingsAlDelayText.setLayoutY(437);

        settingsAlRabbitText.setFont(Font.font("Cascadia Code", 12));
        settingsAlRabbitText.setLayoutX(1118);
        settingsAlRabbitText.setLayoutY(372);

        settingsAlSpawnChanceText.setFont(Font.font("Cascadia Code", 12));
        settingsAlSpawnChanceText.setLayoutX(1059);
        settingsAlSpawnChanceText.setLayoutY(399);

        settingsAlLifeTimeText.setFont(Font.font("Cascadia Code", 12));
        settingsAlLifeTimeText.setLayoutX(1058);
        settingsAlLifeTimeText.setLayoutY(474);

        stopMoveAlText.setFont(Font.font("Cascadia Code", 12));
        stopMoveAlText.setLayoutX(1058);
        stopMoveAlText.setLayoutY(540);

        threadPriorityAlText.setFont(Font.font("Cascadia Code", 12));
        threadPriorityAlText.setLayoutX(1150);
        threadPriorityAlText.setLayoutY(588);

        //main thread
        threadPriorityMainText.setFont(Font.font("Cascadia Code", 12));
        threadPriorityMainText.setLayoutX(1084);
        threadPriorityMainText.setLayoutY(588);

        root.getChildren().addAll(settingsCrDelayText, settingsCrRabbitText, settingsCrSpawnChanceText,
                settingsAlDelayText, settingsAlRabbitText, settingsAlSpawnChanceText, settingsAlLifeTimeText,
                settingsCrLifeTimeText, stopMoveAlText, stopMoveCrText, threadPriorityAlText, threadPriorityCrText, threadPriorityMainText);
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
        editMenu.getItems().add(terminal);

        fileMenu.getItems().addAll(saveSubMenu,loadSubMenu);

        loadSubMenu.getItems().addAll(loadMenuItem,loadDBMenuItem);
        saveSubMenu.getItems().addAll(saveMenuItem,saveDBMenuItem);


        fileMenu.getItems().add(exitMenuItem);

        viewMenu.getItems().addAll(hideShowMenuItem, showAliveRabbits, threadEditMenuItem);

        helpMenu.getItems().add(helpItem);

        root.getChildren().add(menuBar);
    }
}

