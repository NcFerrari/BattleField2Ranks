package lp.fx.tabs;

import javafx.scene.control.Tab;
import lp.enums.TextFXEnum;

public class StatsPane extends Tab {

    public StatsPane() {
        super();
        setText(TextFXEnum.TAB_MENU_STATS.getText(textProperty()));
    }
}
