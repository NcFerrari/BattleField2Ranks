package lp.fx.tabs;

import javafx.scene.control.Tab;
import lp.enums.TextFXEnum;

public class LeaderboardPane extends Tab {

    public LeaderboardPane() {
        super();
        setText(TextFXEnum.TAB_MENU_LEADERBOARDS.getText(textProperty()));
    }
}
