package lp.enums;

public enum TextEnum {
    PANE_STYLES("css/pane-styles.css");

    private final String text;

    TextEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
