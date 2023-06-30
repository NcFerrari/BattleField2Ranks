package lp.fx.tabcontents;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Data;
import lp.Manager;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;
import lp.fx.tabs.Valueable;

@Data
public class BF2OneThirdPane implements Valueable {

    private static final long COMBO_BOX_COUNTER_TIME = 2000;

    private final VBox mainPane = new VBox();
    private final Manager manager = Manager.getInstance();

    private Label playerNameTitle;
    private ComboBox<String> nameComboBox;
    private String letters = TextEnum.EMPTY_STRING.getText();
    private long timeCount;
    private HBox rankPane;
    private VBox rankDataPane;
    private Label rankTitle;
    private Label currentRank;
    private Label nextRank;

    public BF2OneThirdPane() {
        manager.registerValueable(this);
        mainPane.getStyleClass().add(TextEnum.ONE_THIRD_STYLE.getText());

        playerNameTitle = new Label();
        playerNameTitle.setText(TextFXEnum.PLAYER_NAME_TITLE.getText(playerNameTitle.textProperty()));
        playerNameTitle.getStyleClass().add(TextEnum.TITLE_STYLE.getText());
        mainPane.getChildren().add(playerNameTitle);

        nameComboBox = new ComboBox<>();
        mainPane.getChildren().add(nameComboBox);

        initRankPane();
    }

    @Override
    public void reloadData() {
        manager.getComponentsForLanguage().replace(
                currentRank.textProperty(), TextFXEnum.getRank(manager.getSelectedPlayer().getRank()));
        manager.getComponentsForLanguage().replace(
                nextRank.textProperty(), TextFXEnum.getRank(manager.getSelectedPlayer().getRank() + 1));
    }

    public void resizeComponent(double frameWidth, double frameHeight) {
        mainPane.setMinSize(frameWidth / 3, frameHeight);
        mainPane.setMaxSize(mainPane.getMinWidth(), mainPane.getMinHeight());
        playerNameTitle.setMinWidth(frameWidth / 3 - 2);
        rankPane.setMinWidth(frameWidth / 3 - 2);
        rankTitle.setMinWidth(frameWidth / 3 - 2);
        nameComboBox.setMinWidth(frameWidth / 3 - 2);
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
                manager.setSelectedPlayer(newValue));
    }

    private void initRankPane() {
        rankPane = new HBox();
        mainPane.getChildren().add(rankPane);

        rankDataPane = new VBox();

        rankTitle = new Label();
        rankTitle.setText(TextFXEnum.RANK_TITLE.getText(rankTitle.textProperty()));
        rankTitle.getStyleClass().add(TextEnum.SUB_TITLE_STYLE.getText());
        rankDataPane.getChildren().add(rankTitle);

        addLabel(TextFXEnum.CURRENT_RANK, false);
        currentRank = addLabel(TextFXEnum.EMPTY_STRING, true);
        addLabel(TextFXEnum.EMPTY_STRING, false);
        addLabel(TextFXEnum.NEXT_RANK, false);
        nextRank = addLabel(TextFXEnum.EMPTY_STRING, true);
        addLabel(TextFXEnum.EMPTY_STRING, false);
        addLabel(TextFXEnum.PROGRESS_TOWARDS_NEXT_RANK, false);

        rankPane.getChildren().add(rankDataPane);
    }

    private Label addLabel(TextFXEnum textFXEnum, boolean valueFromDB) {
        Label label = new Label();
        if (valueFromDB) {
            label.getStyleClass().add(TextEnum.DB_VALUE_STYLE.getText());
        } else {
            label.getStyleClass().add(TextEnum.VALUE_STYLE.getText());
        }
        label.setText(textFXEnum.getText(label.textProperty()));
        rankDataPane.getChildren().add(label);
        return label;
    }
}
