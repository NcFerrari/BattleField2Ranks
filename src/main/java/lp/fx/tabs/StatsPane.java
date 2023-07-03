package lp.fx.tabs;

import javafx.scene.input.KeyEvent;
import lp.enums.TextFXEnum;

public class StatsPane extends BF2Component {

    public StatsPane() {
        tab.setText(TextFXEnum.TAB_MENU_STATS.getText(tab.textProperty()));
    }

    @Override
    public void resize(double a, double b) {
    }

    @Override
    public void addKeyFocus(KeyEvent evt) {

    }
}
