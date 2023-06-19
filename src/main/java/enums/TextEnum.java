package enums;

import lombok.Getter;

@Getter
public enum TextEnum {
    PANE_STYLES("css/pane-styles.css");

    private final String text;

    TextEnum(String text) {
        this.text = text;
    }

}
