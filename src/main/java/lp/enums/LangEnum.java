package lp.enums;

import lombok.Getter;

@Getter
public enum LangEnum {

    CZ("Česky"),
    EN("English");

    private final String lang;

    LangEnum(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return getLang();
    }
}
