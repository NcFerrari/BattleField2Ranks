package lp.fx.tabs;

import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;
import lp.enums.TextFXEnum;
import lp.fx.tabcontents.BF2OneThirdPane;

public class KitInfoPane extends BF2Component {

    private final BF2OneThirdPane bf2OneThirdPane;

    public KitInfoPane(ObservableList<String> playerNames) {
        tab.setText(TextFXEnum.TAB_MENU_KIT_INFO.getText(tab.textProperty()));
        bf2OneThirdPane = new BF2OneThirdPane();
        bf2OneThirdPane.fillNameComboBox(playerNames);
        tab.setContent(bf2OneThirdPane.getMainPane());
    }

    @Override
    public void resize(double windowWidth, double windowHeight) {
        bf2OneThirdPane.resizeComponent(windowWidth, windowHeight);
    }

    @Override
    public void addKeyFocus(KeyEvent evt) {
        int selectedIndex = bf2OneThirdPane.getNameComboBox().getSelectionModel().getSelectedIndex();
        switch (evt.getCode()) {
            case UP:
                if (selectedIndex > 0) {
                    bf2OneThirdPane.getNameComboBox().getSelectionModel().select(selectedIndex - 1);
                }
                break;
            case DOWN:
                if (selectedIndex < bf2OneThirdPane.getNameComboBox().getItems().size()) {
                    bf2OneThirdPane.getNameComboBox().getSelectionModel().select(selectedIndex + 1);
                }
                break;
            default:
        }
    }
}
