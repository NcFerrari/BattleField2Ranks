package lp.fx.tabContents;

import javafx.scene.layout.VBox;

public class BF2OneThirdPane extends VBox {

    public BF2OneThirdPane() {
        super();
        setStyle("-fx-border-color: yellow");
    }

    public void resizeComponent(double frameWidth, double frameHeight) {
        setMinSize(frameWidth / 3, frameHeight);
        setMaxSize(getMinWidth(), getMinHeight());
    }
}
