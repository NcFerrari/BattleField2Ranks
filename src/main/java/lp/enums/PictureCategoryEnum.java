package lp.enums;

import lombok.Getter;

@Getter
public enum PictureCategoryEnum {
    BADGES_BRONZE("pictures/badges/bronze/badge_", ".jpg"),
    BADGES_SILVER("pictures/badges/silver/badge_", ".jpg"),
    BADGES_GOLD("pictures/badges/gold/badge_", ".jpg"),
    MEDALS("pictures/medals/medal_", ".jpg"),
    RANKS("pictures/ranks/", ".jpg"),
    RIBBONS("pictures/ribbons/ribbon_", ".jpg"),
    SMALL_RIBBONS("pictures/smallRibbons/ribbon_", ".png"),
    SMALL_RANKS("pictures/smallRanks/", ".png"),
    NO_AWARD("pictures/NO_AWARD", ".png");

    private final String path;
    private final String suffix;

    PictureCategoryEnum(String path, String suffix) {
        this.path = path;
        this.suffix = suffix;
    }
}
