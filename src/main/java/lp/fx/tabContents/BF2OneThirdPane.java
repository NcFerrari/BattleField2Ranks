package lp.fx.tabContents;

import javafx.scene.layout.VBox;

public class BF2OneThirdPane extends VBox {

    public BF2OneThirdPane() {

    }

    public void resize(double frameWidth, double frameHeight) {
        setPrefSize(frameWidth / 3, frameHeight);
    }
}
