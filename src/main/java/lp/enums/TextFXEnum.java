package lp.enums;

import javafx.beans.property.StringProperty;
import lp.Manager;

public enum TextFXEnum {
    TAB_MENU_KIT_INFO("KIT INFO", "INFORMACE O VÝBAVĚ"),
    TAB_MENU_STATS("STATS", "STATISTIKY"),
    TAB_MENU_LEADERBOARDS("LEADERBOARDS", "ŽEBŘÍČKY"),
    TAB_MENU_AWARDS("AWARDS", "ODMĚNY"),
    MAIN_APPLICATION_TITLE("Battlefi eld 2 - Head Quarters", "Battlefield 2 - Informační přehledy"),
    PLAYER_NAME_TITLE("PLAYER NAME", "JMÉNO HRÁČE");

    private final String engText;
    private final String czeText;
    private final Manager manager = Manager.getInstance();

    TextFXEnum(String engText, String czeText) {
        this.engText = engText;
        this.czeText = czeText;
    }

    public String getText(StringProperty stringProperty) {
        manager.getComponentsForLanguage().putIfAbsent(stringProperty, this);
        return reloadText();
    }

    public String reloadText() {
        if (manager.getLanguage() == LangEnum.EN) {
            return engText;
        }
        return czeText;
    }
}
