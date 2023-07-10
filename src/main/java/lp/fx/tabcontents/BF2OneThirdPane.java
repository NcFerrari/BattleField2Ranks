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
import lp.business.dto.Player;
import lp.enums.PictureCategoryEnum;
import lp.enums.RankEnum;
import lp.enums.TextEnum;
import lp.enums.TextFXEnum;
import lp.fx.tabs.Valuable;
import lp.service.PictureService;
import lp.serviceimpl.PictureServiceImpl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Data
public class BF2OneThirdPane implements Valuable {

    private static final long COMBO_BOX_COUNTER_TIME = 2000;

    private final VBox mainPane = new VBox();
    private final Manager manager = Manager.getInstance();
    private final PictureService pictureService = new PictureServiceImpl();
    private final Map<TextFXEnum, Label> personalInfoLabels = new EnumMap<>(TextFXEnum.class);
    private final List<ImageView> awards = new ArrayList<>();
    private String letters = TextEnum.EMPTY_STRING.getText();

    private Label playerNameTitle;
    private ComboBox<String> nameComboBox;
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
    private Label personalInfoTitle;
    private Label lastThreeAwardsTitle;

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
        initPersonalInfoPane();
        initLastThreeAwards();
        fillNameComboBox();
    }

    @Override
    public void reloadData() {
        Player player = manager.getSelectedPlayer();
        if (manager.getSelectedPlayer() == null) {
            return;
        }
        int rank = player.getRank();
        manager.getComponentsForLanguage().replace(
                currentRank.textProperty(), RankEnum.getRank(rank));
        manager.getComponentsForLanguage().replace(
                nextRank.textProperty(), RankEnum.getRank(rank + 1));
        rankImage.setImage(pictureService.getAwardImage(PictureCategoryEnum.SMALL_RANKS, rank));
        double totalScore = 20_000;
        if (rank < 22) {
            totalScore = RankEnum.getRank(rank + 1).getGoalScore() - (double) RankEnum.getRank(rank).getGoalScore();
        }
        double currentScore = player.getScore() - (double) RankEnum.getRank(rank).getGoalScore();
        progressBar.setProgress(currentScore / totalScore);

        personalInfoLabels.get(TextFXEnum.GLOBAL_SCORE).setText(String.valueOf(player.getScore()));
        personalInfoLabels.get(TextFXEnum.TIME).setText(formatTime(player.getTime().intValue()));
        personalInfoLabels.get(TextFXEnum.KILLS).setText(String.valueOf(player.getKills()));
        personalInfoLabels.get(TextFXEnum.DEATHS).setText(String.valueOf(player.getDeaths()));
        personalInfoLabels.get(TextFXEnum.TEAM_KILLS).setText(String.valueOf(player.getTeamkills()));
        personalInfoLabels.get(TextFXEnum.WINS).setText(String.valueOf(player.getWins()));
        personalInfoLabels.get(TextFXEnum.LOSSES).setText(String.valueOf(player.getLosses()));
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
        nameComboBox.setPrefWidth(oneThird);
        imagePane.setPrefWidth(oneSixth);
        rankImage.setFitWidth(oneThird / 3);
        rankImage.setFitHeight(oneThird / 3);
        progressPane.setMinWidth(oneThird);
        progressPane.setMaxWidth(oneThird);
        progressBar.setPrefWidth(oneSixth);
        personalInfoTitle.setPrefWidth(oneThird);
        lastThreeAwardsTitle.setPrefWidth(oneThird);
        awards.forEach(imageView -> {
            imageView.setFitWidth(oneThird / 3 - 20);
            imageView.setFitHeight(oneThird / 6);
        });
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

    private void initPersonalInfoPane() {
        personalInfoTitle = new Label();
        personalInfoTitle.setText(TextFXEnum.PERSONAL_INFO.getText(personalInfoTitle.textProperty()));
        personalInfoTitle.getStyleClass().add(TextEnum.SUB_TITLE_STYLE.getText());
        mainPane.getChildren().add(personalInfoTitle);

        addBorderLine(TextFXEnum.GLOBAL_SCORE);
        addBorderLine(TextFXEnum.TIME);
        addBorderLine(TextFXEnum.KILLS);
        addBorderLine(TextFXEnum.DEATHS);
        addBorderLine(TextFXEnum.TEAM_KILLS);
        addBorderLine(TextFXEnum.WINS);
        addBorderLine(TextFXEnum.LOSSES);
    }

    private void addBorderLine(TextFXEnum textFXEnum) {
        BorderPane borderPane = new BorderPane();
        if (personalInfoLabels.size() % 2 == 0) {
            borderPane.getStyleClass().add(TextEnum.BORDER_LIGHT_STYLE.getText());
        }
        mainPane.getChildren().add(borderPane);

        Label textLabel = new Label();
        textLabel.setText(textFXEnum.getText(textLabel.textProperty()));
        textLabel.getStyleClass().add(TextEnum.VALUE_STYLE.getText());
        borderPane.setLeft(textLabel);
        Label valueLabel = new Label();
        valueLabel.getStyleClass().add(TextEnum.DB_VALUE_STYLE.getText());
        borderPane.setRight(valueLabel);
        personalInfoLabels.put(textFXEnum, valueLabel);
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

    private String formatTime(int time) {
        int days = 0;
        int hours = 0;
        int min = 0;
        if (time >= 86400) {
            days = time / 86400;
            time = time % 86400;
        }
        if (time > 3600) {
            hours = time / 3600;
            time = time % 3600;
        }
        if (time > 60) {
            min = time / 60;
            time = time % 60;
        }
        return String.format(TextEnum.TIME_TEXT_FORMAT.getText(),
                days > 0 ? days + TextEnum.DAYS_LETTER.getText() : TextEnum.EMPTY_STRING.getText(),
                hours > 0 ? hours + TextEnum.HOURS_LETTER.getText() : TextEnum.EMPTY_STRING.getText(),
                min > 0 ? min + TextEnum.MINUTES_LETTER.getText() : TextEnum.EMPTY_STRING.getText(),
                time + TextEnum.SECONDS_LETTER.getText());
    }

    private void initLastThreeAwards() {
        lastThreeAwardsTitle = new Label();
        lastThreeAwardsTitle.setText(TextFXEnum.LAST_THREE_AWARDS_TITLE.getText(lastThreeAwardsTitle.textProperty()));
        lastThreeAwardsTitle.getStyleClass().add(TextEnum.SUB_TITLE_STYLE.getText());
        mainPane.getChildren().add(lastThreeAwardsTitle);

        HBox awardsPane = new HBox();
        awardsPane.setSpacing(20);
        awardsPane.getStyleClass().add(TextEnum.AWARDS_PANE_STYLE.getText());
        mainPane.getChildren().add(awardsPane);

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(pictureService.getAwardImage(PictureCategoryEnum.BADGES_GOLD, 2));
            awards.add(imageView);
            awardsPane.getChildren().add(imageView);
        }
    }
}
