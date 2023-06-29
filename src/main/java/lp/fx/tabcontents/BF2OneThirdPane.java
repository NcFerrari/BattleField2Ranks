package lp.fx.tabcontents;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Data;
import lp.Manager;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;

@Data
public class BF2OneThirdPane {

    private static final long COMBO_BOX_COUNTER_TIME = 2000;

    private VBox vBox;
    private Label playerNameTitle;
    private ComboBox<String> nameComboBox;
    private String letters = TextEnum.EMPTY_STRING.getText();
    private long timeCount;

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

    public void fillNameComboBox(ObservableList<String> names) {
        nameComboBox.getItems().addAll(names);
        nameComboBox.setOnKeyPressed(evt -> {
            if (timeCount < (System.currentTimeMillis() - COMBO_BOX_COUNTER_TIME)) {
                timeCount = System.currentTimeMillis();
                letters = TextEnum.EMPTY_STRING.getText();
            }
            letters += evt.getText();
            for (String item : names) {
                if (item.toLowerCase().trim().startsWith(letters.toLowerCase().trim())) {
                    nameComboBox.getSelectionModel().select(item);
                    break;
                }
            }
        });
        nameComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
                Manager.getInstance().setSelectedPlayer(newValue));
    }
}
