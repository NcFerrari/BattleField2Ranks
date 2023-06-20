package lp.fx.tabs;

import javafx.scene.control.Tab;
import lp.enums.TextFXEnum;

public class KitInfoPane extends Tab {

    public KitInfoPane() {
        super();
        setText(TextFXEnum.TAB_MENU_KIT_INFO.getText(textProperty()));
    }
}
