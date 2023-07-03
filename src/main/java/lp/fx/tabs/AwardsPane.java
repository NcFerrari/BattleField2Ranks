package lp.fx.tabs;

import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lp.enums.TextFXEnum;

@Getter
public class AwardsPane extends BF2Component {

    public AwardsPane() {
        tab.setText(TextFXEnum.TAB_MENU_AWARDS.getText(tab.textProperty()));
    }

    @Override
    public void resize(double a, double b) {

    }

    @Override
    public void addKeyFocus(KeyEvent evt) {

    }
}
