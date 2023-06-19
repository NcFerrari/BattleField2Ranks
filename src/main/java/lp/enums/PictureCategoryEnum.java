package lp.enums;

import lombok.Getter;

@Getter
public enum PictureCategoryEnum {
    BADGES("badges/"),
    MEDALS("medals/"),
    RANKS("ranks/"),
    RIBBONS("ribbons"),
    NO_AWARD("/");

    private final String path;

    PictureCategoryEnum(String path) {
        this.path = path;
    }
}
