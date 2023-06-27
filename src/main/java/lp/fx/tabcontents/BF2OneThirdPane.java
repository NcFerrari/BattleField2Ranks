package lp.fx.tabcontents;

import javafx.scene.layout.VBox;
import lombok.Data;

@Data
public class BF2OneThirdPane {

    private VBox vBox;

    public BF2OneThirdPane() {
        vBox = new VBox();
        vBox.setStyle("-fx-border-color: yellow");
    }

    public void resizeComponent(double frameWidth, double frameHeight) {
        vBox.setMinSize(frameWidth / 3, frameHeight);
        vBox.setMaxSize(vBox.getMinWidth(), vBox.getMinHeight());
    }
}
