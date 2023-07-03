package lp.fx.tabs;

import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import lombok.Getter;

@Getter
public abstract class BF2Component {

    protected final Tab tab;

    BF2Component() {
        tab = new Tab();
    }

    public abstract void resize(double windowWidth, double windowHeight);

    public abstract void addKeyFocus(KeyEvent evt);
}
