package lp.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;
import lp.fx.tabs.AwardsPane;
import lp.fx.tabs.KitInfoPane;
import lp.fx.tabs.LeaderboardPane;
import lp.fx.tabs.StatsPane;
import org.apache.log4j.Logger;
import service.LoggerService;
import serviceimpl.LoggerServiceImpl;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainApp extends Application {

    private static final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2.0; //19 * SCREEN_SIZE.width / 20.0
    private static final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 2.0; //19 * SCREEN_SIZE.height / 20.0
    private final Manager manager = Manager.getInstance();
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(MainApp.class);
    private final Logger log = loggerService.getLog();

    private Stage stage;
    private VBox mainPane;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle(TextFXEnum.MAIN_APPLICATION_TITLE.getText(stage.titleProperty()));
        mainPane = new VBox();
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        setCssFile(scene, TextEnum.PANE_STYLES.getText());
        Thread t = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    setCssFile(scene, TextEnum.PANE_STYLES.getText());
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("test");
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
        FlowPane langPane = new FlowPane();
        langPane.setAlignment(Pos.CENTER_RIGHT);
        langPane.getChildren().add(manager.setLanguageChoiceBox(new ComboBox<>()));

        mainPane.getChildren().add(langPane);
    }

    private void resize() {
        mainPane.setPrefHeight(stage.getHeight());
        mainPane.setPrefWidth(stage.getWidth());
    }

    private void setTabPane(VBox mainPain) {
        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(WIDTH, HEIGHT);
        mainPain.getChildren().add(tabPane);
        getTabs().forEach(tab -> {
            tab.setText(tab.getText());
            tabPane.getTabs().add(tab);
            tab.setClosable(false);
        });
    }

    private void setCssFile(Scene scene, String path) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(path);
//            Objects.requireNonNull(MainApp.class.getClassLoader().getResource(path)).toExternalForm());
    }

    private List<Tab> getTabs() {
        List<Tab> resultList = new ArrayList<>();
        resultList.add(new KitInfoPane());
        resultList.add(new StatsPane());
        resultList.add(new LeaderboardPane());
        resultList.add(new AwardsPane());
        return resultList;
    }
}
