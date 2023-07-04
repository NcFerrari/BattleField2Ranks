package lp.enums;

import lombok.Getter;
import lp.Manager;

@Getter
public enum RankEnum implements FXText {
    PRIVATE("Private", "Soukromý", 0),
    PRIVATE_FIRST_CLASS("Private First Class", "Vojín", 150),
    LANCE_CORPORAL("Lance Corporal", "Svobodník", 500),
    CORPORAL("Corporal", "Desátník", 800),
    SERGEANT("Sergeant", "Četař", 2_500),
    STAFF_SERGEANT("Staff Sergeant", "Rotný", 5_000),
    GUNNERY_SERGEANT("Gunnery Sergeant", "Četař dělostřelectva", 8_000),
    MASTER_SERGEANT("Master Sergeant", "Vrchní rotmistr", 20_000),
    FIRST_SERGEANT("First Sergeant", "První rotmistr", 20_000),
    MASTER_GUNNERY_SERGEANT("Master Gunnery Sergeant", "Vrchní četař dělostřelectva", 50_000),
    SERGEANT_MAJOR("Sergeant Major", "Hlavní četař", 50_000),
    SERGEANT_MAJOR_OF_THE_CORPS("Sergeant Major of the Corps", "Hlavní četař sboru", 50_000),
    SECOND_LIEUTENANT("2nd Lieutenant", "Poručík", 60_000),
    FIRST_LIEUTENANT("1st Lieutenant", "Nadporučík", 75_000),
    CAPTAIN("Captain", "Kapitán", 90_000),
    MAJOR("Major", "Major", 11_500),
    LIEUTENANT_COLONEL("Lieutenant Colonel", "Podplukovník", 12_500),
    COLONEL("Colonel", "Plukovník", 15_000),
    BRIGADIER_GENERAL("Brigadier General", "Brigádní generál", 18_000),
    MAJOR_GENERAL("Major General", "Generálmajor", 18_000),
    LIEUTENANT_GENERAL("Lieutenant General", "Generálporučík", 20_000),
    GENERAL("General", "Generál", 20_000),
    EMPTY_STRING("", "", 0);

    private final String engText;
    private final String czeText;
    private final int goalScore;

    RankEnum(String engText, String czeText, int goalScore) {
        this.engText = engText;
        this.czeText = czeText;
        this.goalScore = goalScore;
    }

    public static RankEnum getRank(int rankNumberValue) {
        switch (rankNumberValue) {
            case 0:
                return PRIVATE;
            case 1:
                return PRIVATE_FIRST_CLASS;
            case 2:
                return LANCE_CORPORAL;
            case 3:
                return CORPORAL;
            case 4:
                return SERGEANT;
            case 5:
                return STAFF_SERGEANT;
            case 6:
                return GUNNERY_SERGEANT;
            case 7:
                return MASTER_SERGEANT;
            case 8:
                return FIRST_SERGEANT;
            case 9:
                return MASTER_GUNNERY_SERGEANT;
            case 10:
                return SERGEANT_MAJOR;
            case 11:
                return SERGEANT_MAJOR_OF_THE_CORPS;
            case 12:
                return SECOND_LIEUTENANT;
            case 13:
                return FIRST_LIEUTENANT;
            case 14:
                return CAPTAIN;
            case 15:
                return MAJOR;
            case 16:
                return LIEUTENANT_COLONEL;
            case 17:
                return COLONEL;
            case 18:
                return BRIGADIER_GENERAL;
            case 19:
                return MAJOR_GENERAL;
            case 20:
                return LIEUTENANT_GENERAL;
            case 21:
                return GENERAL;
            default:
                return EMPTY_STRING;
        }
    }

    @Override
    public String reloadText() {
        if (Manager.getInstance().getLanguage() == LangEnum.EN) {
            return engText;
        }
        return czeText;
    }
}
