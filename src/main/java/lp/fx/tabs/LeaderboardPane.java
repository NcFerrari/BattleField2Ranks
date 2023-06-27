package lp.fx.tabs;

import lp.enums.TextFXEnum;

public class LeaderboardPane extends BF2Component {

    public LeaderboardPane() {
        tab.setText(TextFXEnum.TAB_MENU_LEADERBOARDS.getText(tab.textProperty()));
    }

    @Override
    public void resize(double a, double b) {

    }
}
