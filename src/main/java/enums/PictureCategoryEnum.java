package enums;

import lombok.Getter;

@Getter
public enum PictureCategoryEnum {
    BADGES("badges/"),
    MEDALS("medals/"),
    RANKS("ranks/"),
    RIBBONS("ribbons"),
    NO_AWARD("/");

    private String path;

    PictureCategoryEnum(String path) {
        this.path = path;
    }
}
