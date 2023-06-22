package lp.fx.tabs;

import javafx.scene.control.Tab;
import lp.Manager;
import lp.enums.TextFXEnum;
import lp.fx.tabContents.BF2OneThirdPane;

public class KitInfoPane extends Tab {

    private final Manager manager = Manager.getInstance();

    public KitInfoPane() {
        super();
        setText(TextFXEnum.TAB_MENU_KIT_INFO.getText(textProperty()));
        BF2OneThirdPane bf2OneThirdPane = new BF2OneThirdPane();
        setContent(bf2OneThirdPane);
    }
}
