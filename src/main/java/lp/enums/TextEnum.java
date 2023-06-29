package lp.enums;

public enum TextEnum {
    PANE_STYLES("file:///C://temp/pane-styles.css"),
    TITLE_STYLE("title"),
    EMPTY_STRING(""),
    NAME("name"),
    APP_STARTED("Application starting");

    private final String text;

    TextEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
