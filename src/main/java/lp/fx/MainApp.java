package lp.fx;

import generator.service.LoggerService;
import generator.serviceimpl.LoggerServiceImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.enums.LangEnum;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;
import lp.fx.tabs.AwardsPane;
import lp.fx.tabs.BF2Component;
import lp.fx.tabs.KitInfoPane;
import lp.fx.tabs.LeaderboardPane;
import lp.fx.tabs.StatsPane;
import org.apache.log4j.Logger;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private static final double WIDTH = 3 * Toolkit.getDefaultToolkit().getScreenSize().width / 4.0; //19 * SCREEN_SIZE.width / 20.0
    private static final double HEIGHT = 3 * Toolkit.getDefaultToolkit().getScreenSize().height / 4.0; //19 * SCREEN_SIZE.height / 20.0

    private final Manager manager = Manager.getInstance();
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(MainApp.class);
    private final Logger log = loggerService.getLog();
    private final List<BF2Component> bf2Components = new ArrayList<>();

    private Stage stage;
    private VBox mainPane;
    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) {
        log.info(TextEnum.APP_STARTED.getText());
        stage = primaryStage;
        stage.setTitle(TextFXEnum.MAIN_APPLICATION_TITLE.getText(stage.titleProperty()));
        mainPane = new VBox();
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        setCssFile(scene, TextEnum.PANE_STYLES.getText());
        Thread t = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> setCssFile(scene, TextEnum.PANE_STYLES.getText()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.setDaemon(true);
        t.start();
        stage.setScene(scene);
        stage.show();

        setLanguageChooser();
        setTabPane();

        setListeners();
        resize();
    }

    private void setListeners() {
        stage.widthProperty().addListener((observableValue, oldWidth, newWidth) -> resize());
        stage.heightProperty().addListener((observableValue, oldHeight, newHeight) -> resize());
        stage.maximizedProperty().addListener((observableValue, oldHeight, newHeight) -> resize());
    }

    private void setLanguageChooser() {
        FlowPane topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER_RIGHT);

        Button updateButton = new Button();
        updateButton.setText(TextFXEnum.UPDATE_DATA_BUTTON.getText(updateButton.textProperty()));
        updateButton.setOnAction(evt -> manager.loadPlayersFromDB());
        topPane.getChildren().add(updateButton);

        ComboBox<LangEnum> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll(LangEnum.EN, LangEnum.CZ);
        languageComboBox.getSelectionModel().selectFirst();
        languageComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
                manager.reloadLanguages(newValue));
        topPane.getChildren().add(languageComboBox);

        mainPane.getChildren().add(topPane);
    }

    private void resize() {
        mainPane.setPrefHeight(stage.getHeight());
        mainPane.setPrefWidth(stage.getWidth());

        tabPane.setTabMinWidth(stage.getWidth() / 5.824);
        tabPane.setTabMinHeight(stage.getHeight() / 16.98);
        tabPane.setStyle("-fx-font-size: " + stage.getHeight() / 32.65);
        double space = stage.getHeight() - (stage.getHeight() - stage.getHeight() / 32.4) - 37;
        bf2Components.forEach(bf2Component -> bf2Component.resize(
                stage.getWidth(), stage.getHeight() - tabPane.getTabMinHeight() - space - 120));
    }

    private void setTabPane() {
        tabPane = new TabPane();
        mainPane.getChildren().add(tabPane);
        tabPane.setOnKeyPressed(evt -> {
            if (evt.getCode() == KeyCode.UP) {
                if (tabPane.getSelectionModel().isSelected(tabPane.getTabs().size() - 1)) {
                    tabPane.getSelectionModel().selectFirst();
                } else {
                    tabPane.getSelectionModel().select(tabPane.getSelectionModel().getSelectedIndex() + 1);
                }
            } else if (evt.getCode() == KeyCode.DOWN) {
                if (tabPane.getSelectionModel().isSelected(0)) {
                    tabPane.getSelectionModel().selectLast();
                } else {
                    tabPane.getSelectionModel().select(tabPane.getSelectionModel().getSelectedIndex() - 1);
                }
            }
            bf2Components.get(tabPane.getSelectionModel().getSelectedIndex()).addKeyFocus(evt);
        });
        initTabs();
        bf2Components.forEach(bf2Component -> {
            tabPane.getTabs().add(bf2Component.getTab());
            bf2Component.getTab().setClosable(false);
        });
    }

    /**
     * Objects.requireNonNull(MainApp.class.getClassLoader().getResource(path)).toExternalForm());
     */
    private void setCssFile(Scene scene, String path) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(path);
    }

    private void initTabs() {
        bf2Components.add(new KitInfoPane());
        bf2Components.add(new StatsPane());
        bf2Components.add(new LeaderboardPane());
        bf2Components.add(new AwardsPane());
    }
}
