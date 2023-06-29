package lp.fx.tabcontents;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Data;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;

import java.util.List;

@Data
public class BF2OneThirdPane {

    private VBox vBox;
    private Label playerNameTitle;
    private ComboBox<String> nameComboBox;

    public BF2OneThirdPane() {
        vBox = new VBox();

        playerNameTitle = new Label();
        playerNameTitle.getStyleClass().add(TextEnum.TITLE_STYLE.getText());
        playerNameTitle.setText(TextFXEnum.PLAYER_NAME_TITLE.getText(playerNameTitle.textProperty()));
        vBox.getChildren().add(playerNameTitle);

        nameComboBox = new ComboBox<>();
        vBox.getChildren().add(nameComboBox);
        nameComboBox.getSelectionModel().selectFirst();
    }

    public void resizeComponent(double frameWidth, double frameHeight) {
        vBox.setMinSize(frameWidth / 3, frameHeight);
        vBox.setMaxSize(vBox.getMinWidth(), vBox.getMinHeight());
        playerNameTitle.setMinWidth(frameWidth / 3);
        nameComboBox.setMinWidth(frameWidth / 3);
        nameComboBox.setMaxWidth(nameComboBox.getMinWidth());
    }

    public void fillNameComboBox(List<String> names) {
        nameComboBox.getItems().addAll(names);
    }
}
