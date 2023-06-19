package lp.enums;

import javafx.beans.property.StringProperty;
import lp.Manager;

public enum TextFXEnum {
    TAB_MENU_KIT_INFO("Kit Info", "Informace o výbavě"),
    TAB_MENU_STATS("Stats", "Statistiky"),
    TAB_MENU_LEADERBOARDS("Leaderboards", "Žebříčky"),
    TAB_MENU_AWARDS("Awards", "Odměny");

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
