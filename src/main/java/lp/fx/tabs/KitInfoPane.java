package lp.fx.tabs;

import lp.enums.TextFXEnum;
import lp.fx.tabContents.BF2OneThirdPane;

public class KitInfoPane extends BF2Component {

    private final BF2OneThirdPane bf2OneThirdPane;

    public KitInfoPane() {
        tab.setText(TextFXEnum.TAB_MENU_KIT_INFO.getText(tab.textProperty()));
        bf2OneThirdPane = new BF2OneThirdPane();
        tab.setContent(bf2OneThirdPane);
    }

    @Override
    public void resize(double windowWidth, double windowHeight) {
        bf2OneThirdPane.resizeComponent(windowWidth, windowHeight);
    }
}
