package fx;

import enums.TextEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;

public class MainApp extends Application {

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double WIDTH = 19 * SCREEN_SIZE.width / 20.0;
    private static final double HEIGHT = 19 * SCREEN_SIZE.height / 20.0;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        setCssFile(scene, TextEnum.PANE_STYLES.getText());
        stage.setScene(scene);
        stage.show();
    }

    private void setCssFile(Scene scene, String path) {
        scene.getStylesheets().add(
                Objects.requireNonNull(MainApp.class.getClassLoader().getResource(path)).toExternalForm());
    }
}
