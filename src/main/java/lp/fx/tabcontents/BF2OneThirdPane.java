package lp.fx.tabcontents;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Data;
import lp.Manager;
import lp.enums.PictureCategoryEnum;
import lp.enums.RankEnum;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;
import lp.fx.tabs.Valuable;
import lp.service.PictureService;
import lp.serviceimpl.PictureServiceImpl;

@Data
public class BF2OneThirdPane implements Valuable {

    private static final long COMBO_BOX_COUNTER_TIME = 2000;

    private final VBox mainPane = new VBox();
    private final Manager manager = Manager.getInstance();
    private PictureService pictureService = new PictureServiceImpl();

    private Label playerNameTitle;
    private ComboBox<String> nameComboBox;
    private String letters = TextEnum.EMPTY_STRING.getText();
    private long timeCount;
    private HBox rankCenterPane;
    private VBox rankDataPane;
    private Label rankTitle;
    private Label currentRank;
    private Label nextRank;
    private StackPane imagePane;
    private ImageView rankImage;
    private BorderPane progressPane;
    private ProgressBar progressBar;

    public BF2OneThirdPane() {
        manager.registerValuable(this);
        mainPane.getStyleClass().add(TextEnum.ONE_THIRD_STYLE.getText());

        playerNameTitle = new Label();
        playerNameTitle.setText(TextFXEnum.PLAYER_NAME_TITLE.getText(playerNameTitle.textProperty()));
        playerNameTitle.getStyleClass().add(TextEnum.TITLE_STYLE.getText());
        mainPane.getChildren().add(playerNameTitle);

        nameComboBox = new ComboBox<>();
        mainPane.getChildren().add(nameComboBox);

        initRankPane();
        fillNameComboBox();
    }

    @Override
    public void reloadData() {
        if (manager.getSelectedPlayer() == null) {
            return;
        }
        int rank = manager.getSelectedPlayer().getRank();
        manager.getComponentsForLanguage().replace(
                currentRank.textProperty(), RankEnum.getRank(rank));
        manager.getComponentsForLanguage().replace(
                nextRank.textProperty(), RankEnum.getRank(rank + 1));
        rankImage.setImage(pictureService.getAwardImage(PictureCategoryEnum.SMALL_RANKS, rank));
        double totalScore = 20_000;
        if (rank < 22) {
            totalScore = RankEnum.getRank(rank + 1).getGoalScore() - (double) RankEnum.getRank(rank).getGoalScore();
        }
        double currentScore = manager.getSelectedPlayer().getScore() - (double) RankEnum.getRank(rank).getGoalScore();
        progressBar.setProgress(currentScore / totalScore);
    }

    public void resizeComponent(double frameWidth, double frameHeight) {
        double oneThird = frameWidth / 3;
        double oneSixth = frameWidth / 6;
        mainPane.setMinSize(oneThird, frameHeight);
        mainPane.setMaxSize(mainPane.getMinWidth(), mainPane.getMinHeight());
        playerNameTitle.setMinWidth(oneThird);
        rankCenterPane.setMinWidth(oneThird);
        rankDataPane.setMinWidth(oneSixth);
        rankTitle.setPrefWidth(oneThird);
        nameComboBox.setMinWidth(oneThird);
        nameComboBox.setMaxWidth(nameComboBox.getMinWidth());
        imagePane.setMinWidth(oneSixth);
        imagePane.setMaxWidth(oneSixth);
        rankImage.setFitWidth(oneThird / 3);
        rankImage.setFitHeight(oneThird / 3);
        progressPane.setMinWidth(oneThird);
        progressPane.setMaxWidth(oneThird);
        progressBar.setMinWidth(oneSixth);
        progressBar.setMaxWidth(oneSixth);
    }

    private void fillNameComboBox() {
        nameComboBox.getItems().addAll(manager.getPlayerNames());
        nameComboBox.setOnKeyPressed(evt -> {
            if (timeCount < (System.currentTimeMillis() - COMBO_BOX_COUNTER_TIME)) {
                timeCount = System.currentTimeMillis();
                letters = TextEnum.EMPTY_STRING.getText();
            }
            letters += evt.getText();
            for (String item : nameComboBox.getItems()) {
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
        rankTitle = new Label();
        rankTitle.setText(TextFXEnum.RANK_TITLE.getText(rankTitle.textProperty()));
        rankTitle.getStyleClass().add(TextEnum.SUB_TITLE_STYLE.getText());
        mainPane.getChildren().add(rankTitle);

        rankCenterPane = new HBox();
        mainPane.getChildren().add(rankCenterPane);
        rankDataPane = new VBox();
        addLabel(rankDataPane, TextFXEnum.CURRENT_RANK, false);
        currentRank = addLabel(rankDataPane, TextFXEnum.EMPTY_STRING, true);
        addLabel(rankDataPane, TextFXEnum.EMPTY_STRING, false);
        addLabel(rankDataPane, TextFXEnum.NEXT_RANK, false);
        nextRank = addLabel(rankDataPane, TextFXEnum.EMPTY_STRING, true);
        addLabel(rankDataPane, TextFXEnum.EMPTY_STRING, false);
        rankCenterPane.getChildren().add(rankDataPane);

        imagePane = new StackPane();
        imagePane.getStyleClass().add(TextEnum.RANK_IMAGE_STYLE.getText());
        rankImage = new ImageView();
        imagePane.getChildren().add(rankImage);
        rankCenterPane.getChildren().add(imagePane);

        progressPane = new BorderPane();
        mainPane.getChildren().add(progressPane);
        Label progressTowLabel = new Label();
        progressTowLabel.getStyleClass().add(TextEnum.VALUE_STYLE.getText());
        progressTowLabel.setText(TextFXEnum.PROGRESS_TOWARDS_NEXT_RANK.getText(progressTowLabel.textProperty()));
        progressPane.setLeft(progressTowLabel);
        progressBar = new ProgressBar();
        progressPane.setRight(progressBar);
    }

    private Label addLabel(Pane rootPane, TextFXEnum textFXEnum, boolean valueFromDB) {
        Label label = new Label();
        if (valueFromDB) {
            label.getStyleClass().add(TextEnum.DB_VALUE_STYLE.getText());
        } else {
            label.getStyleClass().add(TextEnum.VALUE_STYLE.getText());
        }
        label.setText(textFXEnum.getText(label.textProperty()));
        rootPane.getChildren().add(label);
        return label;
    }
}
