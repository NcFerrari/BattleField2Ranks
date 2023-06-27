package lp.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;
import lp.fx.tabs.AwardsPane;
import lp.fx.tabs.BF2Component;
import lp.fx.tabs.KitInfoPane;
import lp.fx.tabs.LeaderboardPane;
import lp.fx.tabs.StatsPane;
import org.apache.log4j.Logger;
import service.LoggerService;
import serviceimpl.LoggerServiceImpl;

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
    private Scene scene;
    private FlowPane langPane;
    private VBox mainPane;
    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle(TextFXEnum.MAIN_APPLICATION_TITLE.getText(stage.titleProperty()));
        mainPane = new VBox();
        scene = new Scene(mainPane, WIDTH, HEIGHT);
        setCssFile(scene, TextEnum.PANE_STYLES.getText());
        Thread t = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> setCssFile(scene, TextEnum.PANE_STYLES.getText()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        });
        t.setDaemon(true);
        t.start();
        stage.setScene(scene);
        stage.show();

        setLanguageChooser(mainPane);
        setTabPane(mainPane);

        setListeners(stage);
        resize();
    }

    private void setListeners(Stage stage) {
        stage.widthProperty().addListener((observableValue, oldWidth, newWidth) -> resize());
        stage.heightProperty().addListener((observableValue, oldHeight, newHeight) -> resize());
    }

    private void setLanguageChooser(VBox mainPane) {
        langPane = new FlowPane();
        langPane.setAlignment(Pos.CENTER_RIGHT);
        langPane.getChildren().add(manager.setLanguageChoiceBox(new ComboBox<>()));

        mainPane.getChildren().add(langPane);
    }

    private void resize() {
        mainPane.setPrefHeight(scene.getHeight());
        mainPane.setPrefWidth(scene.getWidth());

        tabPane.setTabMinWidth(scene.getWidth() / 5.76);
        tabPane.setTabMinHeight(scene.getHeight() / 16.2);
        tabPane.setStyle("-fx-font-size: " + scene.getHeight() / 32.4);
        double space = scene.getHeight() - (scene.getHeight() - scene.getHeight() / 32.4) - 37;
        bf2Components.forEach(bf2Component -> bf2Component.resize(
                scene.getWidth(), scene.getHeight() - tabPane.getTabMinHeight() - space));
    }

    private void setTabPane(VBox mainPain) {
        tabPane = new TabPane();
        mainPain.getChildren().add(tabPane);
        initTabs();
        bf2Components.forEach(bf2Component -> {
            tabPane.getTabs().add(bf2Component.getTab());
            bf2Component.getTab().setClosable(false);
        });
    }

    private void setCssFile(Scene scene, String path) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(path);
//            Objects.requireNonNull(MainApp.class.getClassLoader().getResource(path)).toExternalForm());
    }

    private void initTabs() {
        bf2Components.add(new KitInfoPane());
        bf2Components.add(new StatsPane());
        bf2Components.add(new LeaderboardPane());
        bf2Components.add(new AwardsPane());
    }
}
