package lp.fx.tabs;

import javafx.scene.control.Tab;
import lp.enums.TextFXEnum;

public class AwardsPane extends Tab {

    public AwardsPane() {
        super();
        setText(TextFXEnum.TAB_MENU_AWARDS.getText(textProperty()));
    }
}
