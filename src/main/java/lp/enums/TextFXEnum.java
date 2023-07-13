package lp.enums;

import javafx.beans.property.StringProperty;
import lp.Manager;

public enum TextFXEnum implements FXText {
    EMPTY_STRING("", ""),
    UPDATE_DATA_BUTTON("Update Data", "Aktualizovat Data"),
    TAB_MENU_KIT_INFO("KIT INFO", "INFORMACE O VÝBAVĚ"),
    TAB_MENU_STATS("STATS", "STATISTIKY"),
    TAB_MENU_LEADERBOARDS("LEADERBOARDS", "ŽEBŘÍČKY"),
    TAB_MENU_AWARDS("AWARDS", "ODMĚNY"),
    MAIN_APPLICATION_TITLE("Battlefield 2 - Head Quarters", "Battlefield 2 - Informační přehledy"),
    PLAYER_NAME_TITLE("PLAYER NAME", "JMÉNO HRÁČE"),
    RANK_TITLE("RANK", "HODNOST"),
    CURRENT_RANK("Current rank", "Aktuální hodnost"),
    NEXT_RANK("Next rank", "Následující hodnost"),
    PROGRESS_TOWARDS_NEXT_RANK("Progress towards next rank", "Postup pro další hodnost"),
    PERSONAL_INFO("PERSONAL INFO", "OSOBNÍ INFORMACE"),
    GLOBAL_SCORE("Global score", "Globální skóre"),
    TIME("Total time", "Celkový čas"),
    KILLS("Kills", "Počet zabití"),
    DEATHS("Deaths", "Počet smrtí"),
    TEAM_KILLS("Team kills", "Zabití kamaráda"),
    WINS("Wins", "Výhry"),
    LOSSES("Losses", "Prohry"),
    LAST_THREE_AWARDS_TITLE("THREE LATEST AWARDS", "POSLEDNÍ TŘI OCENĚNÍ"),
    LAST_AWARD("LATEST AWARD", "POSLEDNÍ OCENĚNÍ");


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

    @Override
    public String reloadText() {
        if (manager.getLanguage() == LangEnum.EN) {
            return engText;
        }
        return czeText;
    }
}
